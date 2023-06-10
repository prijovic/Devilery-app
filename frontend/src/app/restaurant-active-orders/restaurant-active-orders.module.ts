import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RestaurantActiveOrdersRoutingModule } from './restaurant-active-orders-routing.module';
import { StoreModule } from '@ngrx/store';
import * as fromRestaurantActiveOrders from './store/restaurant-active-orders.reducer';
import { RestaurantActiveOrdersComponent } from './components/restaurants-active-orders-container/restaurant-active-orders/restaurant-active-orders.component';
import { OrderCardComponent } from './components/restaurants-active-orders-container/restaurant-active-orders/order-card/order-card.component';
import { CdkDrag, CdkDropList, CdkDropListGroup } from '@angular/cdk/drag-drop';
import { RestaurantsActiveOrdersContainerComponent } from './components/restaurants-active-orders-container/restaurants-active-orders-container.component';

@NgModule({
  declarations: [
    RestaurantActiveOrdersComponent,
    OrderCardComponent,
    RestaurantsActiveOrdersContainerComponent,
  ],
  imports: [
    CommonModule,
    RestaurantActiveOrdersRoutingModule,
    StoreModule.forFeature(
      'restaurant-active-orders',
      fromRestaurantActiveOrders.reducer
    ),
    CdkDropListGroup,
    CdkDropList,
    CdkDrag,
  ],
})
export class RestaurantActiveOrdersModule {}
