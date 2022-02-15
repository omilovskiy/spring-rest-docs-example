package com.demo.springrestdocs.dto;

import com.demo.springrestdocs.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetails {

  private String userId;
  private String email;
  private UserRole role;
}
