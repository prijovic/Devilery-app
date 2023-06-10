import { NgModule } from '@angular/core';

import { RestaurantsRoutingModule } from './restaurants-routing.module';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecommendedRestaurantsComponent } from './components/restaurants/restaurants-container/recommended-restaurants/recommended-restaurants.component';
import { RestaurantCardComponent } from './components/restaurants/restaurants-container/restaurant-card-container/restaurant-card/restaurant-card.component';
import {SharedModule} from "../shared/shared.module";
import { RestaurantCardFooterComponent } from './components/restaurants/restaurants-container/restaurant-card-container/restaurant-card/restaurant-card-footer/restaurant-card-footer.component';
import { RestaurantCardContentComponent } from './components/restaurants/restaurants-container/restaurant-card-container/restaurant-card/restaurant-card-content/restaurant-card-content.component';
import {StoreModule} from "@ngrx/store";
import * as fromRestaurants from "./store/restaurants.reducer";
import { RestaurantTypeCardComponent } from './components/restaurants/restaurants-container/restaurant-types/restaurant-type-card/restaurant-type-card.component';
import { RestaurantTypesComponent } from './components/restaurants/restaurants-container/restaurant-types/restaurant-types.component';
import { SearchResultRestaurantsComponent } from './components/restaurants/restaurants-container/search-result-restaurants/search-result-restaurants.component';
import { RestaurantCardContainerComponent } from './components/restaurants/restaurants-container/restaurant-card-container/restaurant-card-container.component';
import { RestaurantsContainerComponent } from './components/restaurants/restaurants-container/restaurants-container.component';
import { RestaurantProfileComponent } from './components/restaurants/restaurant-profile/restaurant-profile.component';
import { RestaurantDetailsContainerComponent } from './components/restaurants/restaurant-profile/restaurant-details-container/restaurant-details-container.component';
import { RestaurantDetailsComponent } from './components/restaurants/restaurant-profile/restaurant-details-container/restaurant-details/restaurant-details.component';
import { RestaurantViewWithImageComponent } from './components/restaurants/restaurant-view-with-image/restaurant-view-with-image.component';
import { RestaurantItemsContainerComponent } from './components/restaurants/restaurant-profile/restaurant-items-container/restaurant-items-container.component';
import { RestaurantSearchFieldComponent } from './components/restaurants/restaurants-container/restaurant-search-field/restaurant-search-field.component';
import { TextSearchResultRestaurantsComponent } from './components/restaurants/restaurants-container/text-search-result-restaurants/text-search-result-restaurants.component';

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
    RestaurantsContainerComponent,
    RestaurantProfileComponent,
    RestaurantDetailsContainerComponent,
    RestaurantDetailsComponent,
    RestaurantViewWithImageComponent,
    RestaurantItemsContainerComponent,
    RestaurantSearchFieldComponent,
    TextSearchResultRestaurantsComponent,
  ],
  imports: [
    RestaurantsRoutingModule,
    SharedModule,
    StoreModule.forFeature('restaurants', fromRestaurants.reducer),
  ]
})
export class RestaurantsModule { }
