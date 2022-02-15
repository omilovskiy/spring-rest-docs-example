package com.demo.springrestdocs.dto;

import com.demo.springrestdocs.enums.UserRole;
import lombok.Data;

@Data
public class UserAdminResponse {

  private String email;
  private String firstName;
  private String lastName;
  private UserRole role;
}
