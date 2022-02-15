package com.demo.springrestdocs.mappers;

import com.demo.springrestdocs.dto.UserAdminResponse;
import com.demo.springrestdocs.entities.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface UserAdminResponseMapper {

  List<UserAdminResponse> map(List<User> users);

  UserAdminResponse map(User user);
}
