package com.example.recipes;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @Service
public class RecipeServicev1 {

    private final RecipeRepository recipeRepository;

    public RecipeServicev1(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe1> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
