package com.example.recipes;

import domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import adapters.api.RecipeResponse;
import adapters.api.UserResponse;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        UserResponse userResponse = userService.getUserByEmail(email);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/{userId}/favorite-recipes")
    public ResponseEntity<User> addFavoriteRecipe(
            @PathVariable String userId,
            @RequestParam String recipeId
    ) {
        User updatedUser = userService.addFavoriteRecipe(userId, recipeId);
        return ResponseEntity.ok(updatedUser);
    }


    @GetMapping("/{userId}/favorite-recipes")
    public ResponseEntity<List<RecipeResponse>> getFavoriteRecipes(@PathVariable String userId) {
        List<RecipeResponse> favoriteRecipes = userService.getFavoriteRecipes(userId);
        return ResponseEntity.ok(favoriteRecipes);
    }
}

