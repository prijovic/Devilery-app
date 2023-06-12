import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderingRoutingModule } from './ordering-routing.module';
import { OrderingComponent } from './components/ordering/ordering.component';
import { NewOrderComponent } from './components/ordering/new-order/new-order.component';
import { SharedModule } from '../shared/shared.module';
import { DeliveryDetailsComponent } from './components/ordering/new-order/delivery-details/delivery-details.component';
import { PaymentMethodsComponent } from './components/ordering/new-order/payment-methods/payment-methods.component';
import { OrderSummaryComponent } from './components/ordering/new-order/order-summary/order-summary.component';
import { ActiveOrdersComponent } from './components/ordering/active-orders/active-orders.component';
import { CdkDrag, CdkDropList, CdkDropListGroup } from '@angular/cdk/drag-drop';

@NgModule({
  declarations: [
    OrderingComponent,
    NewOrderComponent,
    DeliveryDetailsComponent,
    PaymentMethodsComponent,
    OrderSummaryComponent,
    ActiveOrdersComponent,
  ],
  imports: [
    CommonModule,
    OrderingRoutingModule,
    SharedModule,
    CdkDrag,
    CdkDropList,
    CdkDropListGroup,
  ],
})
export class OrderingModule {}
