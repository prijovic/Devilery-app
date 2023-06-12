import { Component, OnInit } from '@angular/core';
import { Order } from '../../../shared/model/order.model';
import { Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectOrders } from '../../store/order-histoy.selectors';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.scss'],
})
export class OrderHistoryComponent implements OnInit {
  orders: Order[] = [];
  private storeSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectOrders)
      .subscribe((orders) => {
        this.orders = orders;
      });
  }
}
