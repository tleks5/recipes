package adapters.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.recipes.Recipe1;

import java.util.List;

public class RecipeIdResponse {

    private final String id;

    private RecipeIdResponse(String id) {
        this.id = id;
    }

    static RecipeIdResponse of(final Recipe1 recipe) {
        return new RecipeIdResponse(recipe.getId());
    }

    @JsonProperty("id")
    String getId() {
        return id;
    }
}

