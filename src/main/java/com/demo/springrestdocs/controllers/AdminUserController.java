package com.demo.springrestdocs.controllers;

import static org.mapstruct.factory.Mappers.getMapper;

import com.demo.springrestdocs.dto.UserAdminResponse;
import com.demo.springrestdocs.dto.UserResponse;
import com.demo.springrestdocs.mappers.UserAdminResponseMapper;
import com.demo.springrestdocs.mappers.UserResponseMapper;
import com.demo.springrestdocs.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

  private static final UserAdminResponseMapper RESPONSE_MAPPER = getMapper(UserAdminResponseMapper.class);

  private final UserService userService;

  @GetMapping
  public List<UserAdminResponse> findAll() {
    return RESPONSE_MAPPER.map(userService.findAll());
  }
}
