import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRestaurantsRoutingModule } from './admin-restaurants-routing.module';
import { AdminRestaurantsComponent } from './components/admin-restaurants/admin-restaurants.component';
import { RestaurantRegistrationFormComponent } from './components/admin-restaurants/restaurant-registration-form/restaurant-registration-form.component';


@NgModule({
  declarations: [
    AdminRestaurantsComponent,
    RestaurantRegistrationFormComponent
  ],
  imports: [
    CommonModule,
    AdminRestaurantsRoutingModule
  ]
})
export class AdminRestaurantsModule { }
