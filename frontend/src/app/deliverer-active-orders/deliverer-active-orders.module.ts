import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DelivererActiveOrdersRoutingModule } from './deliverer-active-orders-routing.module';
import { DelivererActiveOrdersContainerComponent } from './components/deliverer-active-orders-container/deliverer-active-orders-container.component';
import { DelivererActiveOrdersComponent } from './components/deliverer-active-orders-container/deliverer-active-orders/deliverer-active-orders.component';
import { OrderCardComponent } from './components/deliverer-active-orders-container/deliverer-active-orders/order-card/order-card.component';
import { StoreModule } from '@ngrx/store';
import { CdkDrag, CdkDropList, CdkDropListGroup } from '@angular/cdk/drag-drop';
import { SharedModule } from '../shared/shared.module';
import * as fromDelivererActiveOrders from './store/deliverer-active-orders.reducer';

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
      fromDelivererActiveOrders.reducer
    ),
    DelivererActiveOrdersRoutingModule,
    CdkDrag,
    CdkDropList,
    CdkDropListGroup,
    SharedModule,
  ],
})
export class DelivererActiveOrdersModule {}
