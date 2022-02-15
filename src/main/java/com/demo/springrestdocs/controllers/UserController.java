package com.demo.springrestdocs.controllers;

import static org.mapstruct.factory.Mappers.getMapper;

import com.demo.springrestdocs.dto.UserResponse;
import com.demo.springrestdocs.mappers.UserResponseMapper;
import com.demo.springrestdocs.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private static final UserResponseMapper RESPONSE_MAPPER = getMapper(UserResponseMapper.class);

  private final UserService userService;

  @GetMapping
  public List<UserResponse> getUser() {
    return RESPONSE_MAPPER.map(userService.findAll());
  }
}
