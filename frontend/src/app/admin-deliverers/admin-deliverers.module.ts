import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminDeliverersRoutingModule } from './admin-deliverers-routing.module';
import { AdminDeliverersComponent } from './components/admin-deliverers/admin-deliverers.component';
import { DelivererRegistrationFormComponent } from './components/admin-deliverers/deliverer-registration-form/deliverer-registration-form.component';
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    AdminDeliverersComponent,
    DelivererRegistrationFormComponent
  ],
  imports: [
    CommonModule,
    AdminDeliverersRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class AdminDeliverersModule { }
