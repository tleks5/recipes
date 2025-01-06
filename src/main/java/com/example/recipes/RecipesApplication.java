package com.example.recipes;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(Repository repository) {
		return args -> {
			Recipe recipe = new Recipe(
				"Spaghetti Carbonara",
				List.of("spaghetti", "eggs", "bacon", "parmesan cheese", "black pepper"),
				"1. Cook spaghetti according to package instructions. 2. In a bowl, whisk together eggs, parmesan cheese, and black pepper. 3. In a skillet, cook bacon until crispy. 4. Add cooked spaghetti to skillet and toss with bacon. 5. Remove skillet from heat and add egg mixture. 6. Toss spaghetti until eggs are cooked. 7. Serve and enjoy!",
				"Pasta"
			);

			repository.insert(recipe);
		};
	}

}
