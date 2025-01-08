package domain.model;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private List<String> ingredients;
    private String instructions;
    private String category;
    private String authorId;


    public Recipe(String name, List<String> ingredients, String instructions, String category, String authorId) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
// package domain.model;

// import org.springframework.data.mongodb.core.index.Indexed;

// import java.util.List;

// public class Recipe {

//     private final String id;
//     private final String name;
//     private final List<String> ingredients;
//     private final String instructions;
//     private final String category;


//     private Recipe(
//             String id,
//             String name,
//             List<String> ingredients,
//             String instructions,
//             String category
//     ) {
//         this.id = id;
//         this.name = name;
//         this.ingredients = ingredients;
//         this.instructions = instructions;
//         this.category = category;
//     }

//     public String getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public List<String> getIngredients() {
//         return ingredients;
//     }

//     public String getInstructions() {
//         return instructions;
//     }

//     public String getCategory() {
//         return category;
//     }
// }
