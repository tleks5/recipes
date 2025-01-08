package domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ingredients")
public class Ingredient {

    @Id
    private String id;
    private String name;
    private List<String> allergens;

    public Ingredient(String name, List<String> allergens) {
        this.name = name;
        this.allergens = allergens;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getAllergens() {
        return allergens;
    }
}
