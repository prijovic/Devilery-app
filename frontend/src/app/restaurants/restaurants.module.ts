import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RestaurantsRoutingModule } from './restaurants-routing.module';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RecommendedRestaurantsComponent } from './components/restaurants/recommended-restaurants/recommended-restaurants.component';


@NgModule({
  declarations: [
    RestaurantsComponent,
    RecommendedRestaurantsComponent
  ],
  imports: [
    CommonModule,
    RestaurantsRoutingModule
  ]
})
export class RestaurantsModule { }
