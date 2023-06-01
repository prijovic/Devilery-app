import { NgModule } from '@angular/core';

import { RestaurantsRoutingModule } from './restaurants-routing.module';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecommendedRestaurantsComponent } from './components/restaurants/recommended-restaurants/recommended-restaurants.component';
import { RestaurantCardComponent } from './components/restaurants/recommended-restaurants/restaurant-card/restaurant-card.component';
import {SharedModule} from "../shared/shared.module";
import { RestaurantCardFooterComponent } from './components/restaurants/recommended-restaurants/restaurant-card/restaurant-card-footer/restaurant-card-footer.component';
import { RestaurantCardContentComponent } from './components/restaurants/recommended-restaurants/restaurant-card/restaurant-card-content/restaurant-card-content.component';
import {StoreModule} from "@ngrx/store";
import * as fromRestaurants from "./store/restaurants.reducer";


@NgModule({
  declarations: [
    RestaurantsComponent,
    RecommendedRestaurantsComponent,
    RestaurantCardComponent,
    RestaurantCardFooterComponent,
    RestaurantCardContentComponent,
  ],
  imports: [
    RestaurantsRoutingModule,
    SharedModule,
    StoreModule.forFeature('restaurants', fromRestaurants.reducer),
  ]
})
export class RestaurantsModule { }
