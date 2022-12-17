import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Recipe } from 'src/app/model/recipe';
import { UnitOfMeasure } from 'src/app/model/unit-of-measure';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-create-recipe-page',
  templateUrl: './create-recipe-page.component.html',
  styleUrls: ['./create-recipe-page.component.scss']
})
export class CreateRecipePageComponent implements OnInit {

  public newRecipeFormGroup: FormGroup;
  public recipeIngredientsFormArr: FormArray = new FormArray([]);

  recipe: Recipe;

  //todo fetch all ingredients & uoms
  ingredients = [];
  unitOfMeasures = [];

  responseMessage: string;

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.newRecipeFormGroup = new FormGroup({
      name: new FormControl('asd', Validators.required),
      category: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      instructions: new FormControl('', Validators.required)
    })
  }

  postRecipe() {
    this.recipe = this.newRecipeFormGroup.value;
    this.recipe.ingredients = this.recipeIngredientsFormArr.value;
    console.log(this.recipe);

    this.recipeService.postRecipe(this.recipe).subscribe({
      next: (res) => this.responseMessage = "Recipe created",
      error: (err) => {
        this.responseMessage = "Error occured while creating recipe"
        console.error('Recipe post error:', err)
      }
    })
  }

  addRecipeIngredient(index: number) {
    this.recipeIngredientsFormArr.insert(
       index,
       new FormGroup({
          name: new FormControl('', Validators.required),
          quantity: new FormControl('', Validators.required),
          unitOfMeasure: new FormGroup({
            unit: new FormControl('', Validators.required)
          })
       }));
 }

  addIngredientNameDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.name = event.target.value;
  }

  addIngredientQuantityDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.quantity = event.target.value;
  }

  addIngredientUnitOfMeasureDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.unitOfMeasure.unit = event.target.value;
  }

  printRecipeForm() {
    console.log(this.newRecipeFormGroup.value);
    console.log(this.recipeIngredientsFormArr.value);
  }

}
