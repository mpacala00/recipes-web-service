import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Recipe } from 'src/app/model/recipe';
import { UnitOfMeasure } from 'src/app/model/unit-of-measure';

@Component({
  selector: 'app-create-recipe-page',
  templateUrl: './create-recipe-page.component.html',
  styleUrls: ['./create-recipe-page.component.scss']
})
export class CreateRecipePageComponent implements OnInit {

  public newRecipeFormGroup: FormGroup;
  public recipeIngredientsFormArr: FormArray = new FormArray([]);

  recipe: Recipe;

  ingredients = [];
  unitOfMeasures = [];

  constructor() { }

  ngOnInit(): void {
    this.newRecipeFormGroup = new FormGroup({
      name: new FormControl('asd', Validators.required),
      category: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      instructions: new FormControl('', Validators.required)
    })

    //todo fetch all ingredients & uoms
  }

  postRecipe() {
    this.recipe = this.newRecipeFormGroup.value;
    this.recipe.ingredients = this.recipeIngredientsFormArr.value;
    // this.recipe.ingredients.forEach(ing => {
    //   ing.unit = { id: '', unit: ing.unit };
    // })
    console.log(this.recipe);
  }

  addRecipeIngredient(index: number) {
    this.recipeIngredientsFormArr.insert(
       index,
       new FormGroup({
          name: new FormControl('', Validators.required),
          quantity: new FormControl('', Validators.required),
          unit: new FormControl('', Validators.required)
       }));
 }

 addIngredientDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.size = event.target.value;
  }

  printRecipeForm() {
    console.log(this.newRecipeFormGroup.value);
    console.log(this.recipeIngredientsFormArr.value);
  }

}
