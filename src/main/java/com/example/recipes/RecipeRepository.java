package com.example.recipes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import domain.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<Recipe> findByName(String name);

    @Query("{ 'ingredients': { $all: ?0 } }")
    List<Recipe> findByIngredientsContainingAll(List<String> ingredients);

    @Query("{ 'ingredients': { $not: { $elemMatch: { $nin: ?0 } } } }")
    List<Recipe> findByIngredientsInSubsetOf(List<String> ingredients);

    List<Recipe> findByCategory(String category);
}
