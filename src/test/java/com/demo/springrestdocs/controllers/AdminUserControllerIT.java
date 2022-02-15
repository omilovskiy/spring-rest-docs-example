package com.demo.springrestdocs.controllers;

import static com.demo.springrestdocs.utils.DeserializationUtil.deserialize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.springrestdocs.entities.User;
import com.demo.springrestdocs.repositories.UserRepository;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(RestDocumentationExtension.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AdminUserControllerIT {

  private static final String TEST_OUTPUT_DIRECTORY = "{ClassName}/{methodName}";

  @Container
  private static final MongoDBContainer DB_CONTAINER = new MongoDBContainer("mongo:latest");

  @DynamicPropertySource
  private static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", DB_CONTAINER::getReplicaSetUrl);
  }

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MockMvc mockMvc;

  @AfterEach
  public void cleanUp() {
    userRepository.deleteAll();
  }

  @BeforeEach
  public void setup() throws IOException {
    userRepository.save(deserialize("templates/user.json", User.class));
  }

  @Test
  @WithMockUser(authorities = "USER")
  public void givenUser_whenGetRequest_shouldReturn403() throws Exception {

    mockMvc.perform(get("/admin/users"))
        .andExpect(status().isForbidden())
        .andDo(document(TEST_OUTPUT_DIRECTORY, preprocessResponse(prettyPrint())));
  }

  @Test
  @WithMockUser(authorities = "ADMIN")
  public void givenAdmin_whenGetRequest_shouldReturn200() throws Exception {

    mockMvc.perform(get("/admin/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstName").exists())
        .andExpect(jsonPath("$[0].lastName").exists())
        .andExpect(jsonPath("$[0].email").exists())
        .andExpect(jsonPath("$[0].role").exists())
        .andDo(document(TEST_OUTPUT_DIRECTORY, preprocessResponse(prettyPrint())));
  }

  @Test
  public void givenNoAuth_whenGetRequest_shouldRedirect() throws Exception {

    mockMvc.perform(get("/admin/users"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("http://localhost:8080/unauthorized"))
        .andDo(document(TEST_OUTPUT_DIRECTORY, preprocessResponse(prettyPrint())));
  }
}
