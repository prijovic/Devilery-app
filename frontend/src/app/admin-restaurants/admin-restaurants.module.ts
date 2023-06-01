import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRestaurantsRoutingModule } from './admin-restaurants-routing.module';
import { AdminRestaurantsComponent } from './components/admin-restaurants/admin-restaurants.component';
import { RestaurantRegistrationFormComponent } from './components/admin-restaurants/restaurant-registration-form/restaurant-registration-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import { WorkingHoursFieldComponent } from './components/admin-restaurants/restaurant-registration-form/working-hours-field/working-hours-field.component';
import {MaterialModule} from "../material/material.module";


@NgModule({
  declarations: [
    AdminRestaurantsComponent,
    RestaurantRegistrationFormComponent,
    WorkingHoursFieldComponent
  ],
  imports: [
    CommonModule,
    AdminRestaurantsRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class AdminRestaurantsModule { }
