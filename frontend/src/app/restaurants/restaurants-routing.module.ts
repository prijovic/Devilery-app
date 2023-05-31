import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RestaurantsComponent} from "./components/restaurants/restaurants.component";
import {
  RecommendedRestaurantsComponent
} from "./components/restaurants/recommended-restaurants/recommended-restaurants.component";

const routes: Routes = [{
  path: '',
  component: RestaurantsComponent,
  children: [
    {
      path: 'all',
      component: RecommendedRestaurantsComponent
    }]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantsRoutingModule { }
