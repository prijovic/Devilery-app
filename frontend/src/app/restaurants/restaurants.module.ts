import { NgModule } from '@angular/core';

import { RestaurantsRoutingModule } from './restaurants-routing.module';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecommendedRestaurantsComponent } from './components/restaurants/recommended-restaurants/recommended-restaurants.component';
import { RestaurantCardComponent } from './components/restaurants/restaurant-card-container/restaurant-card/restaurant-card.component';
import {SharedModule} from "../shared/shared.module";
import { RestaurantCardFooterComponent } from './components/restaurants/restaurant-card-container/restaurant-card/restaurant-card-footer/restaurant-card-footer.component';
import { RestaurantCardContentComponent } from './components/restaurants/restaurant-card-container/restaurant-card/restaurant-card-content/restaurant-card-content.component';
import {StoreModule} from "@ngrx/store";
import * as fromRestaurants from "./store/restaurants.reducer";
import { RestaurantTypeCardComponent } from './components/restaurants/restaurant-types/restaurant-type-card/restaurant-type-card.component';
import { RestaurantTypesComponent } from './components/restaurants/restaurant-types/restaurant-types.component';
import { SearchResultRestaurantsComponent } from './components/restaurants/search-result-restaurants/search-result-restaurants.component';
import { RestaurantCardContainerComponent } from './components/restaurants/restaurant-card-container/restaurant-card-container.component';


@NgModule({
  declarations: [
    RestaurantsComponent,
    RecommendedRestaurantsComponent,
    RestaurantCardComponent,
    RestaurantCardFooterComponent,
    RestaurantCardContentComponent,
    RestaurantTypeCardComponent,
    RestaurantTypesComponent,
    SearchResultRestaurantsComponent,
    RestaurantCardContainerComponent,
  ],
  imports: [
    RestaurantsRoutingModule,
    SharedModule,
    StoreModule.forFeature('restaurants', fromRestaurants.reducer),
  ]
})
export class RestaurantsModule { }
