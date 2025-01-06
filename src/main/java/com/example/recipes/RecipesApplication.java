package com.example.recipes;

import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
		RecipeRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Recipe recipe = new Recipe(
				"Pierniczki",
				List.of("mąka", "jajka"),
				"coś tam",
				"Deser"
			);
			
			// usingMongoTemplateAndQuery(repository, mongoTemplate, recipe);

			repository.findByName(recipe.getName())
				.ifPresentOrElse(
					r -> System.out.println("Recipe already exists"),
					() -> {
						System.out.println("Inserting recipe " + recipe.getName());
						repository.insert(recipe);
					}
				);
		};
	}

	private void usingMongoTemplateAndQuery(RecipeRepository repository, MongoTemplate mongoTemplate, Recipe recipe) {

		Query query = new Query();
		query.addCriteria(Criteria.where("name").is("Spaghetti Carbonara"));

		List<Recipe> recipes = mongoTemplate.find(query, Recipe.class);

		if (recipes.size() > 1){
			throw new IllegalStateException(
				"This recipe already exists");
		}

		if (recipes.isEmpty()) {
			System.out.println("Inserting recipe " + recipe.getName());
			repository.insert(recipe);
		} else {
			System.out.println("Recipe already exists");
		}
	}

}
