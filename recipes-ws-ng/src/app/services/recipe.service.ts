import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http: HttpClient) { }

  private recipesUrl = `${environment.baseUrl}/recipes`;

  getAllRecipes() {
    return this.http.get<any>(this.recipesUrl);
  }
}
