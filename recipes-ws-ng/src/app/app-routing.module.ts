import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowseRecipesPageComponent } from './components/browse-recipes-page/browse-recipes-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';

const routes: Routes = [
  {
    path: 'browse-recipes',
    component: BrowseRecipesPageComponent,
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
