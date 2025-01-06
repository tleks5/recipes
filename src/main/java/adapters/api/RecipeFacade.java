package adapters.api;

import com.example.recipes.RecipeServicev1;
import domain.model.Recipe;
import domain.ports.RecipeService;
import org.springframework.stereotype.Component;

@Component
public class RecipeFacade {

    private final RecipeService recipeService;

    RecipeFacade(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    RecipeResponse get(final String recipeId) {
        final Recipe recipe = recipeService.get(RecipeId.of(recipeId));
        return RecipeResponse.of(recipe);
    }

    RecipeIdResponse create(final RecipeRequest recipeRequest) {
        final String recipeId = recipeService.create(recipeRequest.name(), articleRequest.title(), articleRequest.content());
        return RecipeIdResponse.of(recipeId);
    }
}
