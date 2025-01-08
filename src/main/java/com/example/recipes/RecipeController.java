package com.example.recipes;
import java.lang.StackWalker.Option;
import java.util.HashSet;
import java.util.List;
import domain.model.Recipe;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import adapters.api.RecipeResponse;


@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeResponse> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/{recipeName}")
    public ResponseEntity<RecipeResponse> getRecipeByName(@PathVariable String recipeName) {
            RecipeResponse recipeResponse = recipeService.getRecipeByName(recipeName);
            return ResponseEntity.ok(recipeResponse);
    }

    @GetMapping("/search")
    public List<RecipeResponse> searchByIngredients(@RequestParam List<String> ingredients) {
        return recipeService.getRecipesByIngredients(ingredients);
    }

    @GetMapping("/search/subset")
    public List<RecipeResponse> findRecipesMatchingSubset(@RequestParam List<String> ingredients) {
        return recipeService.findRecipesByMatchingSubset(ingredients);
    }

    @GetMapping("/by-category")
    public List<RecipeResponse> getRecipesByCategory(@RequestParam String category) {
        return recipeService.getRecipesByCategory(category);
    }

    @GetMapping("/by-author/{authorEmail}")
    public List<RecipeResponse> getRecipesByAuthor(@PathVariable String authorEmail) {
        return recipeService.getRecipesByAuthor(authorEmail);
    }

    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody Recipe recipe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.save(recipe));
    }

    @GetMapping("/without-allergens")
    public ResponseEntity<List<RecipeResponse>> getRecipesWithoutAllergens(@RequestParam List<String> allergens) {
        Set<String> excludedAllergens = new HashSet<>(allergens);
        List<Recipe> recipes = recipeService.getRecipesWithoutAllergens(excludedAllergens);

        List<RecipeResponse> recipeResponses = recipes.stream()
                .map(RecipeResponse::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipeResponses);
    }
    @GetMapping("/filter")
    public String filterRecipes(
            @RequestParam(required = false) String recipeName,
            @RequestParam(required = false) List<String> ingredients,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Set<String> allergens,
            Model model
    ) {
        List<RecipeResponse> filteredRecipes = recipeService.filterRecipes(recipeName, ingredients, category, allergens);
        
        model.addAttribute("recipes", filteredRecipes);
        
        return "index";
    }
    
    

}
