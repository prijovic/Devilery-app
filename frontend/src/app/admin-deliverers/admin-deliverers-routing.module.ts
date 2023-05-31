import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminDeliverersComponent} from "./components/admin-deliverers/admin-deliverers.component";
import {
  DelivererRegistrationFormComponent
} from "./components/admin-deliverers/deliverer-registration-form/deliverer-registration-form.component";

const routes: Routes = [
  {
    path: '',
    component: AdminDeliverersComponent,
    children: [
      {
        path: 'new',
        component: DelivererRegistrationFormComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminDeliverersRoutingModule { }
