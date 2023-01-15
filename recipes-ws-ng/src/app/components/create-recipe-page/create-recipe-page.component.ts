import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Recipe } from 'src/app/model/recipe';
import { UnitOfMeasure } from 'src/app/model/unit-of-measure';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-create-recipe-page',
  templateUrl: './create-recipe-page.component.html',
  styleUrls: ['./create-recipe-page.component.scss'],
})
export class CreateRecipePageComponent implements OnInit {
  public newRecipeFormGroup: FormGroup;
  public recipeIngredientsFormArr: FormArray = new FormArray([]);
  public alertType = 'success';

  imageFile: File;
  recipe: Recipe;

  //todo fetch all ingredients & uoms
  ingredients = [];
  unitOfMeasures = [];
  fetchNutrition: boolean = false;

  setFetchNutrition($event: any) {
    this.fetchNutrition = $event.srcElement.checked;
  }

  responseMessage: string;

  constructor(
    private recipeService: RecipeService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.newRecipeFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      instructions: new FormControl('', Validators.required),
    });
  }

  postRecipe() {
    this.recipe = this.newRecipeFormGroup.value;
    this.recipe.ingredients = this.recipeIngredientsFormArr.value;
    console.log(this.recipe);

    let recipeLocation = this.recipeService
      .postRecipe(this.recipe, this.fetchNutrition)
      .subscribe({
        next: (res) => {
          this.alertType = 'success';
          this.responseMessage = 'Recipe created';
          let recipeLocation = res.headers.get('Location');

          if (!recipeLocation) {
            return;
          }

          let recipeId = recipeLocation.split('/')[2];
          console.log('RECIPE ID');
          this.uploadImage(recipeId);
        },
        error: (err) => {
          this.alertType = 'danger';
          this.responseMessage = 'Error occured while creating recipe';
          console.error('Recipe post error:', err);
        },
      });
  }

  addRecipeIngredient(index: number) {
    this.recipeIngredientsFormArr.insert(
      index,
      new FormGroup({
        name: new FormControl('', Validators.required),
        quantity: new FormControl('', Validators.required),
        unitOfMeasure: new FormGroup({
          unit: new FormControl('', Validators.required),
        }),
      })
    );
  }

  addIngredientNameDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.name = event.target.value;
  }

  addIngredientQuantityDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.quantity = event.target.value;
  }

  addIngredientUnitOfMeasureDetails(event: any, index: number) {
    this.recipeIngredientsFormArr.at(index).value.unitOfMeasure.unit =
      event.target.value;
  }

  deleteIngredient(event: any, index: number) {
    this.recipeIngredientsFormArr.removeAt(index);
    this.cdr.detectChanges();
  }

  printRecipeForm() {
    console.log(this.newRecipeFormGroup.value);
    console.log(this.recipeIngredientsFormArr.value);
  }

  updateImage($event: any) {
    $event.preventDefault();
    this.imageFile = $event.target.files[0];
    console.log('imageFile', this.imageFile);
  }

  uploadImage(recipeId: string) {
    const imageFormData = new FormData();
    imageFormData.append('imageFile', this.imageFile, this.imageFile.name);

    this.recipeService.uploadRecipeImage(recipeId, imageFormData).subscribe({
      next: (res) => console.log('image uploaded'),
      error: (err) => {
        this.responseMessage = 'Error occured while posting image';
        console.error('Recipe image upload error:', err);
      },
    });
  }
}
