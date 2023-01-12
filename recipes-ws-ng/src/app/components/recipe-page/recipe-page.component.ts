import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/model/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.scss'],
})
export class RecipePageComponent implements OnInit {
  constructor(private router: Router, private recipeService: RecipeService) {}

  recipe: Recipe;

  ngOnInit(): void {
    // if (!history.state.recipe) {
    //   this.router.navigate(['/']); //return to home page if refreshed
    // }

    // this.recipe = history.state.recipe;
    let recipeId = this.router.url.split('/recipe/')[1];
    this.recipeService.getRecipeById(recipeId).subscribe({
      next: (res) => {
        this.recipe = res;
      },
      error: (err) => console.error('error while fetching recipe', err),
    });
  }
}
