package adapters.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recipes")
public class RecipeEndpoint {
    private final RecipeFacade recipes;

    RecipeEndpoint(RecipeFacade recipes) {
        this.recipes = recipes;
    }

    @GetMapping("{recipeName}")
    RecipeResponse get(@PathVariable("recipeName") final String recipeName) {
        return recipes.get(recipeName);
    }

    @PostMapping
    RecipeIdResponse create(@RequestBody final RecipeRequest recipeRequest) {
        return recipes.create(recipeRequest);
    }
}
