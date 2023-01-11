import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Recipe } from '../model/recipe';

@Injectable({
  providedIn: 'root',
})
export class RecipeService {
  constructor(private http: HttpClient) {}

  private recipesUrl = `${environment.baseUrl}/recipes`;

  getAllRecipes() {
    return this.http.get<Recipe[]>(this.recipesUrl);
  }

  postRecipe(recipe: Recipe) {
    return this.http.post<Recipe>(this.recipesUrl, recipe, {observe: "response"});
  }

  uploadRecipeImage(recipeId: string, imageFormData: FormData) {
    console.log('image url', `${this.recipesUrl}/${recipeId}/image`)
    return this.http.post<FormData>(`${this.recipesUrl}/${recipeId}/image`, imageFormData);
  }
}
