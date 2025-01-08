package adapters.api;

import domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserResponse {
    private final String username;
    private final String email;
    private final List<String> favoriteRecipes;

    private UserResponse(String username, String email, List<String> favoriteRecipes) {
        this.username = username;
        this.email = email;
        this.favoriteRecipes = favoriteRecipes;
    }

    public static UserResponse of(User user) {
        return new UserResponse(
            user.getUsername(),
            user.getEmail(),
            user.getFavoriteRecipes()
        );
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("favoriteRecipes")
    public List<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }
}

