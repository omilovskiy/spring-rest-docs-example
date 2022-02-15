package com.demo.springrestdocs.mappers;

import com.demo.springrestdocs.dto.UserResponse;
import com.demo.springrestdocs.entities.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface UserResponseMapper {

  List<UserResponse> map(List<User> users);

  UserResponse map(User user);
}
