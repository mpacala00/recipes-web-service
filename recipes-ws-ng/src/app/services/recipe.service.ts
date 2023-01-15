import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Recipe } from '../model/recipe';
import { RecipePage } from '../model/recipe-page';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  constructor(private http: HttpClient) {}

  private recipesUrl = `${environment.baseUrl}/recipes`;

  getAllRecipes() {
    return this.http.get<Recipe[]>(this.recipesUrl);
  }

  getLatestRecipes() {
    return this.http.get<RecipePage>(`${this.recipesUrl}/latest`);
  }

  postRecipe(recipe: Recipe, fetchNutrition: boolean) {
    return this.http.post<Recipe>(
      `${this.recipesUrl}?fetchNutrition=${fetchNutrition}`,
      recipe,
      {
        observe: 'response',
      }
    );
  }

  uploadRecipeImage(recipeId: string, imageFormData: FormData) {
    return this.http.post<FormData>(
      `${this.recipesUrl}/${recipeId}/image`,
      imageFormData
    );
  }

  getRecipeById(recipeId: string) {
    return this.http.get<Recipe>(`${this.recipesUrl}/${recipeId}`);
  }

  deleteRecipeById(recipeId: string) {
    return this.http.delete(`${this.recipesUrl}/${recipeId}`);
  }
}
