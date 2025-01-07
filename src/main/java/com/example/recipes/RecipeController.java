package com.example.recipes;
import java.lang.StackWalker.Option;
import java.util.List;
import domain.model.Recipe;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import adapters.api.RecipeResponse;


@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/{recipeName}")
    public ResponseEntity<RecipeResponse> getRecipeByName(@PathVariable String recipeName) {
            RecipeResponse recipeResponse = recipeService.getRecipeByName(recipeName);
            return ResponseEntity.ok(recipeResponse);
    }

    @GetMapping("/search")
    public List<Recipe> searchByIngredients(@RequestParam List<String> ingredients) {
        return recipeService.getRecipesByIngredients(ingredients);
    }

    @GetMapping("/search/subset")
    public List<Recipe> findRecipesMatchingSubset(@RequestParam List<String> ingredients) {
        return recipeService.findRecipesByMatchingSubset(ingredients);
    }

    @GetMapping("/by-category")
    public List<Recipe> getRecipesByCategory(@RequestParam String category) {
        return recipeService.getRecipesByCategory(category);
    }

    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody Recipe recipe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.save(recipe));
    }
}
