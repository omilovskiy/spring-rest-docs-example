package com.demo.springrestdocs.repositories;

import com.demo.springrestdocs.entities.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmailAndPassword(String username, String password);
}
