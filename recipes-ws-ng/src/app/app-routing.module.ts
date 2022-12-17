import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateRecipePageComponent } from './components/create-recipe-page/create-recipe-page.component';
import { BrowseRecipesPageComponent } from './components/browse-recipes-page/browse-recipes-page.component';

import { HomePageComponent } from './components/home-page/home-page.component';

const routes: Routes = [
  {
    path: 'browse-recipes',
    component: BrowseRecipesPageComponent,
  },
  {
    path: 'create-recipe',
    component: CreateRecipePageComponent
  },
  {
    path: '',
    component: HomePageComponent,
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
