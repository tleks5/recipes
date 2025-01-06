package domain.model;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class Recipe {

    private final String id;
    private final String name;
    private final List<String> ingredients;
    private final String instructions;
    private final String category;


    private Recipe(
            String id,
            String name,
            List<String> ingredients,
            String instructions,
            String category
    ) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
    }
}
