import { Component, OnInit, TrackByFunction } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/model/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent implements OnInit {
  constructor(private recipeService: RecipeService, private router: Router) {}

  recipes: Recipe[] = [];
  recipePage: any;

  ngOnInit(): void {
    this.recipeService.getLatestRecipes().subscribe({
      next: (res) => {
        console.log('recipes:', res);
        this.recipePage = res;
        this.recipes = res.content;
      },
      error: (err) => console.error('error while fetching recipes', err),
    });
  }

  navigateToRecipe(recipeId: string) {
    this.router.navigate([`/recipe/${recipeId}`]);
  }
}
