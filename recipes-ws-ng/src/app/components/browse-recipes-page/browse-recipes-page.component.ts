import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/model/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-browse-recipes-page',
  templateUrl: './browse-recipes-page.component.html',
  styleUrls: ['./browse-recipes-page.component.scss'],
})
export class BrowseRecipesPageComponent implements OnInit {
  constructor(private recipeService: RecipeService) {}

  recipes: Recipe[] = [];

  ngOnInit(): void {
    this.recipeService.getAllRecipes().subscribe({
      next: (res) => {
        this.recipes = res;
      },
      error: (err) => console.error('error while fetching recipes', err)
    }
    );
  }
}
