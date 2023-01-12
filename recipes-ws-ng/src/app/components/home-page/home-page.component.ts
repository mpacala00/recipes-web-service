import { Component, OnInit, TrackByFunction } from '@angular/core';
import { Recipe } from 'src/app/model/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent implements OnInit {
  constructor(private recipeService: RecipeService) {}

  recipes: Recipe[] = [];

  ngOnInit(): void {
    this.recipeService.getAllRecipes().subscribe({
      next: (res) => {
        console.log('recipes:', res);
        this.recipes = res.slice(0, 4);
      },
      error: (err) => console.error('error while fetching recipes', err),
    });
  }
}
