import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/model/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-browse-recipes-page',
  templateUrl: './browse-recipes-page.component.html',
  styleUrls: ['./browse-recipes-page.component.scss'],
})
export class BrowseRecipesPageComponent implements OnInit {
  constructor(private recipeService: RecipeService, private router: Router) {}

  recipes: Recipe[] = [];

  ngOnInit(): void {
    this.recipeService.getAllRecipes().subscribe({
      next: (res) => {
        this.recipes = res;
      },
      error: (err) => console.error('error while fetching recipes', err),
    });
  }

  viewRecipe(recipe: Recipe) {
    this.router.navigate(['/recipe', recipe.id], {
      state: { recipe },
    });
  }
}
