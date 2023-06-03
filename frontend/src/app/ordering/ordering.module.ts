import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderingRoutingModule } from './ordering-routing.module';
import { OrderingComponent } from './components/ordering/ordering.component';
import { NewOrderComponent } from './components/ordering/new-order/new-order.component';
import {SharedModule} from "../shared/shared.module";
import { DeliveryDetailsComponent } from './components/ordering/new-order/delivery-details/delivery-details.component';
import { PaymentMethodsComponent } from './components/ordering/new-order/payment-methods/payment-methods.component';
import { OrderSummaryComponent } from './components/ordering/new-order/order-summary/order-summary.component';


@NgModule({
  declarations: [
    OrderingComponent,
    NewOrderComponent,
    DeliveryDetailsComponent,
    PaymentMethodsComponent,
    OrderSummaryComponent
  ],
  imports: [
    CommonModule,
    OrderingRoutingModule,
    SharedModule
  ]
})
export class OrderingModule { }
