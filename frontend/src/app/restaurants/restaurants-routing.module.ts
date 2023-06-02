import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RestaurantsComponent} from "./components/restaurants/restaurants.component";
import {
  RecommendedRestaurantsComponent
} from "./components/restaurants/restaurants-container/recommended-restaurants/recommended-restaurants.component";
import {
  SearchResultRestaurantsComponent
} from "./components/restaurants/restaurants-container/search-result-restaurants/search-result-restaurants.component";
import {SearchRestaurantsResolver} from "./reslovers/search-restaurants.resolver";
import {
  RestaurantsContainerComponent
} from "./components/restaurants/restaurants-container/restaurants-container.component";
import {RestaurantProfileComponent} from "./components/restaurants/restaurant-profile/restaurant-profile.component";
import {RestaurantsResolver} from "./reslovers/restaurants.resolver";
import {RestaurantItemsResolver} from "./reslovers/restaurant-items.resolver";

const routes: Routes = [{
  path: '',
  component: RestaurantsComponent,
  children: [
    {
      path: 'all',
      component: RestaurantsContainerComponent,
      children: [
        {
          path: '',
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
    },
    {
      path: 'restaurant/:id',
      component: RestaurantProfileComponent,
      resolve: [RestaurantItemsResolver]
    },
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantsRoutingModule { }
