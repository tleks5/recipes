package com.example.recipes;

import org.springframework.data.mongodb.repository.MongoRepository;
import domain.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Optional<Ingredient> findByName(String name);
}
