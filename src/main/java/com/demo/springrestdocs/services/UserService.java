package com.demo.springrestdocs.services;

import com.demo.springrestdocs.entities.User;
import com.demo.springrestdocs.repositories.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User getByCredentials(String email, String password) {
    return userRepository.findByEmailAndPassword(email, password).orElseThrow();
  }
}
