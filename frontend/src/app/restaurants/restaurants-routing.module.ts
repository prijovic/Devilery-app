import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RestaurantsComponent} from "./components/restaurants/restaurants.component";
import {
  RecommendedRestaurantsComponent
} from "./components/restaurants/recommended-restaurants/recommended-restaurants.component";
import {RestaurantsResolver} from "./reslovers/restaurants.resolver";

const routes: Routes = [{
  path: '',
  component: RestaurantsComponent,
  children: [
    {
      path: 'all',
      component: RecommendedRestaurantsComponent,
      resolve: [RestaurantsResolver]
    }]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantsRoutingModule { }
