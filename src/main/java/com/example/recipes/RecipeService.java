package com.example.recipes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.model.Ingredient;
import domain.model.Recipe;

import org.springframework.stereotype.Service;

import adapters.api.RecipeResponse;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;

    }

    public RecipeResponse getRecipeByName(String recipeName){
        Recipe recipe = recipeRepository.findByName(recipeName)
            .orElseThrow(() -> new IllegalArgumentException("Recipe not found with name: " + recipeName));
        return RecipeResponse.of(recipe);
    }

    public List<RecipeResponse> getRecipesByIngredients(List<String> ingredients) {
        return recipeRepository.findByIngredientsContainingAll(ingredients).stream().map(RecipeResponse::of).toList();
    }

    public List<RecipeResponse> findRecipesByMatchingSubset(List<String> ingredients) {
        return recipeRepository.findByIngredientsInSubsetOf(ingredients).stream().map(RecipeResponse::of).toList();
    }

    public List<RecipeResponse> getRecipesByCategory(String category) {
        return recipeRepository.findByCategory(category).stream().map(RecipeResponse::of).toList();
    }

    public List<RecipeResponse> getRecipesByAuthor(String authorEmail) {
        return recipeRepository.findByAuthorId(authorEmail).stream().map(RecipeResponse::of).toList();
    }

    List<RecipeResponse> getRecipes() {
        return recipeRepository.findAll().stream().map(RecipeResponse::of).toList();
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Set<String> detectAllergensInRecipe(Recipe recipe) {
        Set<String> allergens = new HashSet<>();
        for (String ingredientName : recipe.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                    .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + ingredientName));
            allergens.addAll(ingredient.getAllergens());
        }
        return allergens;
    }

    public List<Recipe> getRecipesWithoutAllergens(Set<String> excludedAllergens) {
    List<Recipe> allRecipes = recipeRepository.findAll();

    return allRecipes.stream()
            .filter(recipe -> {
                Set<String> recipeAllergens = detectAllergensInRecipe(recipe);
                return recipeAllergens.stream().noneMatch(excludedAllergens::contains);
            })
            .collect(Collectors.toList());
    }
    public List<RecipeResponse> filterRecipes(
        String recipeName,
        List<String> ingredients,
        String category,
        Set<String> excludedAllergens
    ) {
        List<Recipe> allRecipes = recipeRepository.findAll();

        return allRecipes.stream()
                .filter(recipe -> {

                    if (recipeName != null && !recipeName.isEmpty()) {
                        if (!recipe.getName().equalsIgnoreCase(recipeName)) {
                            return false;
                        }
                    }

                    if (ingredients != null && !ingredients.isEmpty()) {
                        if (!recipe.getIngredients().containsAll(ingredients)) {
                            return false;
                        }
                    }

                    if (category != null && !category.isEmpty()) {
                        if (!recipe.getCategory().equalsIgnoreCase(category)) {
                            return false;
                        }
                    }

                    if (excludedAllergens != null && !excludedAllergens.isEmpty()) {
                        Set<String> recipeAllergens = detectAllergensInRecipe(recipe);
                        if (recipeAllergens.stream().anyMatch(excludedAllergens::contains)) {
                            return false;
                        }
                    }

                    return true; 
                })
                .map(RecipeResponse::of) 
                .toList();
    }

}

