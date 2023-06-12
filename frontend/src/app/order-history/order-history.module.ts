import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderHistoryRoutingModule } from './order-history-routing.module';
import { OrderHistoryComponent } from './components/order-history/order-history.component';
import { SharedModule } from '../shared/shared.module';
import { StoreModule } from '@ngrx/store';
import * as fromOrderHistory from './store/order-history.reducer';

@NgModule({
  declarations: [OrderHistoryComponent],
  imports: [
    CommonModule,
    StoreModule.forFeature('order-history', fromOrderHistory.reducer),
    OrderHistoryRoutingModule,
    SharedModule,
  ],
})
export class OrderHistoryModule {}
