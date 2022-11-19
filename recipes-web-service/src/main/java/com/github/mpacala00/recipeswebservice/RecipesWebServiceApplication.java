package com.github.mpacala00.recipeswebservice;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.mpacala00.recipeswebservice.model.Recipe;
import com.github.mpacala00.recipeswebservice.service.RecipeService;


@SpringBootApplication
public class RecipesWebServiceApplication implements CommandLineRunner {

	@Autowired RecipeService recipeService;

	public static void main(String[] args) {
		SpringApplication.run(RecipesWebServiceApplication.class, args);
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override public void run(String... args) throws Exception {
		this.saveExampleRecipes();
	}

	private void saveExampleRecipes() {
		recipeService.save(new Recipe(UUID.randomUUID().toString(), "Spaghetti Bolognese", "Dinner", Arrays.asList("Pasta", "Tomato sauce", "Ground beef")));
	}
}
