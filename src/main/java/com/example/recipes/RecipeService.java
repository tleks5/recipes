package com.example.recipes;

import java.util.List;
import domain.model.Recipe;

import org.springframework.stereotype.Service;

import adapters.api.RecipeResponse;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeResponse getRecipeByName(String recipeName){
        Recipe recipe = recipeRepository.findByName(recipeName)
                .orElseThrow(() -> new RuntimeException("Recipe not found: " + recipeName));
        return RecipeResponse.of(recipe);
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        return recipeRepository.findByIngredientsContainingAll(ingredients);
    }

    public List<Recipe> findRecipesByMatchingSubset(List<String> ingredients) {
        return recipeRepository.findByIngredientsInSubsetOf(ingredients);
    }
    
    public List<Recipe> getRecipesByCategory(String category) {
        return recipeRepository.findByCategory(category);
    }

    List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

}

