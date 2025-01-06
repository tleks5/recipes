package com.example.recipes;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Recipe, String> {

}
