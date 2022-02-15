package com.demo.springrestdocs.controllers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class IndexControllerIT {

  private static final String TEST_OUTPUT_DIRECTORY = "{ClassName}/{methodName}";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturn200() throws Exception {
    mockMvc.perform(get("/success"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Authorized"))
        .andExpect(jsonPath("$.timestamp").exists())
        .andDo(document(TEST_OUTPUT_DIRECTORY, preprocessResponse(prettyPrint())));
  }

  @Test
  public void shouldReturn401() throws Exception {
    mockMvc.perform(get("/unauthorized"))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Not authorized"))
        .andExpect(jsonPath("$.timestamp").exists())
        .andDo(document(TEST_OUTPUT_DIRECTORY, preprocessResponse(prettyPrint())));
  }
}
