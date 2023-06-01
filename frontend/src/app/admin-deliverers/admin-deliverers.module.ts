import { NgModule } from '@angular/core';

import { AdminDeliverersRoutingModule } from './admin-deliverers-routing.module';
import { AdminDeliverersComponent } from './components/admin-deliverers/admin-deliverers.component';
import { DelivererRegistrationFormComponent } from './components/admin-deliverers/deliverer-registration-form/deliverer-registration-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    AdminDeliverersComponent,
    DelivererRegistrationFormComponent
  ],
  imports: [
    AdminDeliverersRoutingModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class AdminDeliverersModule { }
