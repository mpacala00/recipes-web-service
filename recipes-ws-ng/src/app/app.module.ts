import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavPanelComponent } from './components/nav-panel/nav-panel.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RecipeCardComponent } from './components/home-page/recipe-card/recipe-card.component';
import { CreateRecipePageComponent } from './components/create-recipe-page/create-recipe-page.component';
import { BrowseRecipesPageComponent } from './components/browse-recipes-page/browse-recipes-page.component';
import { RecipePageComponent } from './components/recipe-page/recipe-page.component';

@NgModule({
  declarations: [
    AppComponent,
    NavPanelComponent,
    HomePageComponent,
    RecipeCardComponent,
    CreateRecipePageComponent,
    BrowseRecipesPageComponent,
    RecipePageComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, ReactiveFormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
