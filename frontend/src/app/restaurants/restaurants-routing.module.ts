import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RestaurantsComponent} from "./components/restaurants/restaurants.component";
import {
  RecommendedRestaurantsComponent
} from "./components/restaurants/recommended-restaurants/recommended-restaurants.component";
import {RestaurantsResolver} from "./reslovers/restaurants.resolver";
import {
  SearchResultRestaurantsComponent
} from "./components/restaurants/search-result-restaurants/search-result-restaurants.component";
import {SearchRestaurantsResolver} from "./reslovers/search-restaurants.resolver";

const routes: Routes = [{
  path: '',
  component: RestaurantsComponent,
  children: [
    {
      path: 'all',
      pathMatch: 'full',
      component: RecommendedRestaurantsComponent,
      resolve: [RestaurantsResolver]
    },
    {
      path: ':type',
      component: SearchResultRestaurantsComponent,
      resolve: [SearchRestaurantsResolver]
    },
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantsRoutingModule { }
