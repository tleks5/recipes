package com.example.recipes;

import org.springframework.stereotype.Service;

import domain.model.User;
import adapters.api.RecipeResponse;
import adapters.api.UserResponse;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public UserService(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(UserResponse::of).toList();
    }
    

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return UserResponse.of(user);
    }
    

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + email));
        return UserResponse.of(user);
    }

    public User addFavoriteRecipe(String userId, String recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getFavoriteRecipes().contains(recipeId)) {
            user.getFavoriteRecipes().add(recipeId);
            return userRepository.save(user);
        }
        return user;
    }

    public List<RecipeResponse> getFavoriteRecipes(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<String> favoriteRecipeIds = user.getFavoriteRecipes();
        return recipeRepository.findAllById(favoriteRecipeIds).stream()
                .map(RecipeResponse::of)
                .toList();
    }


}
