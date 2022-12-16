package com.github.mpacala00.recipeswebservice;

import java.util.Arrays;
import java.util.UUID;

import com.github.mpacala00.recipeswebservice.model.Ingredient;
import com.github.mpacala00.recipeswebservice.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.mpacala00.recipeswebservice.model.Recipe;
import com.github.mpacala00.recipeswebservice.service.RecipeService;


@SpringBootApplication
public class RecipesWebServiceApplication implements CommandLineRunner {

	@Autowired RecipeService recipeService;
	@Autowired
	IngredientService ingredientService;

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
		Ingredient ing1 = Ingredient.builder().name("Pasta").quantity(400.0).build();
		Ingredient ing2 = Ingredient.builder().name("Tomato sauce").quantity(500.0).build();
		Ingredient ing3 = Ingredient.builder().name("Ground beef").quantity(500.0).build();

		recipeService.save(Recipe.builder()
				.name("Spaghetti Bolognese")
				.category("Dinner")
				.ingredients(Arrays.asList(
						ing1, ing2, ing3
				)).build()
		);

		ingredientService.save(Ingredient.builder().name("garlic").build());
	}
}
