package com.demo.springrestdocs.entities;

import com.demo.springrestdocs.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class User {

  @Id
  private String id;

  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private UserRole role;
}
