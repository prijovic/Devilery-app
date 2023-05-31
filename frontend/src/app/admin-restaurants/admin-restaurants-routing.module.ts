import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminRestaurantsComponent} from "./components/admin-restaurants/admin-restaurants.component";
import {
  RestaurantRegistrationFormComponent
} from "./components/admin-restaurants/restaurant-registration-form/restaurant-registration-form.component";

const routes: Routes = [
  {
    path: '',
    component: AdminRestaurantsComponent,
    children: [
      {
        path: 'new',
        component: RestaurantRegistrationFormComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRestaurantsRoutingModule { }
