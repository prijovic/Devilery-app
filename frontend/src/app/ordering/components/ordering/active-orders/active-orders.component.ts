import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectActiveOrders } from '../../../store/ordering.selectors';
import { Order } from '../../../../shared/model/order.model';

@Component({
  selector: 'app-active-orders',
  templateUrl: './active-orders.component.html',
  styleUrls: ['./active-orders.component.scss'],
})
export class ActiveOrdersComponent implements OnInit {
  orders: Order[] = [];
  private storeSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectActiveOrders)
      .subscribe((orders) => {
        this.orders = orders;
      });
  }
}
