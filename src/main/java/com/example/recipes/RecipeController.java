package com.example.recipes;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/recipes")
@AllArgsConstructor
public class RecipeController {

    private final RecipeServicev1 recipeService;

    public RecipeController(RecipeServicev1 recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> fetchAllRecipes() {
        return recipeService.getAllRecipes();

    }
}
