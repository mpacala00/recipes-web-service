import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavPanelComponent } from './components/nav-panel/nav-panel.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RecipeCardComponent } from './components/home-page/recipe-card/recipe-card.component';

@NgModule({
  declarations: [
    AppComponent,
    NavPanelComponent,
    HomePageComponent,
    RecipeCardComponent,
  ],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
