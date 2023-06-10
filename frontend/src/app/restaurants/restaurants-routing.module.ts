import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecommendedRestaurantsComponent } from './components/restaurants/restaurants-container/recommended-restaurants/recommended-restaurants.component';
import { SearchResultRestaurantsComponent } from './components/restaurants/restaurants-container/search-result-restaurants/search-result-restaurants.component';
import { SearchRestaurantsResolver } from './resolvers/search-restaurants.resolver';
import { RestaurantsContainerComponent } from './components/restaurants/restaurants-container/restaurants-container.component';
import { RestaurantProfileComponent } from './components/restaurants/restaurant-profile/restaurant-profile.component';
import { RestaurantsResolver } from './resolvers/restaurants.resolver';
import { RestaurantItemsResolver } from './resolvers/restaurant-items.resolver';
import { TextSearchResultRestaurantsComponent } from './components/restaurants/restaurants-container/text-search-result-restaurants/text-search-result-restaurants.component';
import { TextSearchRestaurantsResolver } from './resolvers/text-search-restaurants.resolver';

const routes: Routes = [
  {
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
            resolve: [RestaurantsResolver],
          },
          {
            path: 'search/:text',
            pathMatch: 'full',
            component: TextSearchResultRestaurantsComponent,
            resolve: [TextSearchRestaurantsResolver],
          },
          {
            path: ':type',
            component: SearchResultRestaurantsComponent,
            resolve: [SearchRestaurantsResolver],
          },
        ],
      },
      {
        path: 'restaurant/:id',
        component: RestaurantProfileComponent,
        resolve: [RestaurantItemsResolver],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RestaurantsRoutingModule {}
