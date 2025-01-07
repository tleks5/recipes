package adapters.api;

import domain.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecipeResponse {
    private final String name;
    private final List<String> ingredients;
    private final String instructions;
    private final String category;

    private RecipeResponse(String name, List<String> ingredients, String instructions, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
    }

    public static RecipeResponse of(Recipe recipe) {
        return new RecipeResponse(
            recipe.getName(),
            recipe.getIngredients(),
            recipe.getInstructions(),
            recipe.getCategory()
        );
    }

    @JsonProperty("name")
    String getName() {
        return name;
    }

    @JsonProperty("ingredients")
    List<String> getIngredients() {
        return ingredients;
    }

    @JsonProperty("instructions")
    String getInstructions() {
        return instructions;
    }

    @JsonProperty("category")
    String getCategory() {
        return category;
    }
}
