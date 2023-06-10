import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DelivererActiveOrdersRoutingModule } from './deliverer-active-orders-routing.module';
import { DelivererActiveOrdersContainerComponent } from './components/deliverer-active-orders-container/deliverer-active-orders-container.component';
import { DelivererActiveOrdersComponent } from './components/deliverer-active-orders-container/deliverer-active-orders/deliverer-active-orders.component';
import { OrderCardComponent } from './components/deliverer-active-orders-container/deliverer-active-orders/order-card/order-card.component';
import { StoreModule } from '@ngrx/store';
import * as fromRestaurantActiveOrders from '../restaurant-active-orders/store/restaurant-active-orders.reducer';
import { CdkDrag, CdkDropList, CdkDropListGroup } from '@angular/cdk/drag-drop';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    DelivererActiveOrdersContainerComponent,
    DelivererActiveOrdersComponent,
    OrderCardComponent,
  ],
  imports: [
    CommonModule,
    StoreModule.forFeature(
      'deliverer-active-orders',
      fromRestaurantActiveOrders.reducer
    ),
    DelivererActiveOrdersRoutingModule,
    CdkDrag,
    CdkDropList,
    CdkDropListGroup,
    SharedModule,
  ],
})
export class DelivererActiveOrdersModule {}
