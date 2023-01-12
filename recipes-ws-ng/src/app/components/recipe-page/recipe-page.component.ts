import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/model/recipe';

@Component({
  selector: 'app-recipe-page',
  templateUrl: './recipe-page.component.html',
  styleUrls: ['./recipe-page.component.scss'],
})
export class RecipePageComponent implements OnInit {
  constructor(private router: Router) {}

  @Input()
  recipe: Recipe;

  ngOnInit(): void {
    if (!history.state.recipe) {
      this.router.navigate(['/']); //return to home page if refreshed
    }

    this.recipe = history.state.recipe;
  }
}
