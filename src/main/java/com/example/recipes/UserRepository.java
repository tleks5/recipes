package com.example.recipes;

import org.springframework.data.mongodb.repository.MongoRepository;
import domain.model.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);
}
