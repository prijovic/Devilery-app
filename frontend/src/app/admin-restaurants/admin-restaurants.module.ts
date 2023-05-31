import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRestaurantsRoutingModule } from './admin-restaurants-routing.module';
import { AdminRestaurantsComponent } from './components/admin-restaurants/admin-restaurants.component';
import { RestaurantRegistrationFormComponent } from './components/admin-restaurants/restaurant-registration-form/restaurant-registration-form.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import { WorkingHoursFieldComponent } from './components/admin-restaurants/restaurant-registration-form/working-hours-field/working-hours-field.component';


@NgModule({
  declarations: [
    AdminRestaurantsComponent,
    RestaurantRegistrationFormComponent,
    WorkingHoursFieldComponent
  ],
  imports: [
    CommonModule,
    AdminRestaurantsRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class AdminRestaurantsModule { }
