import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import {
  getActiveRestaurantOrders,
  getRestaurantId,
} from '../../../restaurant-active-orders/store/restaurant-active-orders.actions';
import {
  selectOrdersWithStatus,
  selectRestaurantId,
} from '../../../restaurant-active-orders/store/restaurant-active-orders.selectors';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { BehaviorSubject, Subscription } from 'rxjs';
import { notifyInfo } from '../../../core/store/core.actions';
import { selectDecodedToken } from '../../../auth/store/auth.selectors';
import { getActiveDelivererOrders } from '../../../deliverer-active-orders/store/deliverer-active-orders.actions';

@Component({
  selector: 'app-orders-notifications',
  templateUrl: './orders-notifications.component.html',
  styleUrls: ['./orders-notifications.component.scss'],
})
export class OrdersNotificationsComponent implements OnInit, OnDestroy {
  ordersCount = new BehaviorSubject<number>(0);
  role: string | null = null;
  private stompClient: any;
  private restaurantIdSubscription!: Subscription;
  private activeRestaurantOrdersSubscription!: Subscription;
  private authSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.store.select(selectDecodedToken).subscribe((token) => {
      if (token) {
        this.role = token.role;
        if (this.role === 'DELIVERER') {
          this.initializeWebSocketConnection(token.sub);
        } else if (this.role === 'EMPLOYEE' || this.role === 'OWNER') {
          this.restaurantIdSubscription = this.store
            .select(selectRestaurantId)
            .subscribe((restaurantId) => {
              if (restaurantId) {
                this.store.dispatch(getActiveRestaurantOrders());
                this.initializeWebSocketConnection(restaurantId);
              }
            });
          this.activeRestaurantOrdersSubscription = this.store
            .select(selectOrdersWithStatus('pending'))
            .subscribe((orders) => {
              this.ordersCount.next(orders.length);
            });
          this.store.dispatch(getRestaurantId());
        }
      }
    });
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
    this.restaurantIdSubscription?.unsubscribe();
    this.activeRestaurantOrdersSubscription?.unsubscribe();
    this.stompClient.disconnect();
  }

  initializeWebSocketConnection(id: string) {
    const ws = new SockJS('http://localhost:8080/api/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    if (this.role === 'DELIVERER') {
      this.stompClient.connect({}, () => {
        this.openDelivererSocket(id);
      });
    } else {
      this.stompClient.connect({}, () => {
        this.openOrdersSocket(id);
      });
    }
  }

  openDelivererSocket(email: string) {
    this.stompClient.subscribe(
      '/topic/deliverer-order/' + email,
      (message: { body: string }) => {
        this.store.dispatch(
          notifyInfo({
            message: `New order has been assigned to you. Please pick it up.`,
            title: 'New Order Assigned',
          })
        );
        this.store.dispatch(getActiveDelivererOrders());
      }
    );
  }

  openOrdersSocket(restaurantId: string) {
    this.stompClient.subscribe(
      '/topic/restaurant-order/' + restaurantId,
      (message: { body: string }) => {
        this.store.dispatch(
          notifyInfo({
            message: `New order has arrived. Please resolve it.`,
            title: 'New Order Arrived',
          })
        );
        this.store.dispatch(getActiveRestaurantOrders());
      }
    );
  }
}
