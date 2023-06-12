import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { notifyInfo } from '../../../core/store/core.actions';
import { selectEmail } from '../../../auth/store/auth.selectors';
import { getActiveOrders } from '../../../ordering/store/ordering.actions';
import { Dialog } from '@angular/cdk/dialog';
import { ReviewDialogComponent } from './review-dialog/review-dialog.component';

@Component({
  selector: 'app-active-orders',
  templateUrl: './active-orders.component.html',
  styleUrls: ['./active-orders.component.scss'],
})
export class ActiveOrdersComponent implements OnInit, OnDestroy {
  private stompClient: any;
  private authSubscription!: Subscription;

  constructor(private store: Store, private dialog: Dialog) {}

  ngOnInit() {
    this.authSubscription = this.store
      .select(selectEmail)
      .subscribe((email) => {
        if (email) {
          this.initializeWebSocketConnection(email);
        }
      });
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
    this.stompClient.disconnect();
  }

  initializeWebSocketConnection(email: string) {
    const ws = new SockJS('http://localhost:8080/api/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    this.stompClient.connect({}, () => {
      this.openOrderStatusSocket(email);
    });
  }

  openOrderStatusSocket(email: string) {
    this.stompClient.subscribe(
      '/topic/order-status/' + email,
      (message: { body: string }) => {
        const orderId = message.body.split(' ')[0];
        const orderStatus = message.body.split(' ')[1];
        let notificationMessage = '';
        const title = 'Order Status Changed';
        switch (orderStatus) {
          case 'ACCEPTED':
            notificationMessage =
              'Restaurant has accepted your order. It is being prepared.';
            break;
          case 'REJECTED':
            notificationMessage = 'Restaurant has rejected your order.';
            break;
          case 'DONE':
            notificationMessage =
              'Your order is prepared. It is waiting to be picked up.';
            break;
          case 'BEING_DELIVERED':
            notificationMessage =
              'Your order has been picked up. It is being delivered.';
            break;
          case 'DELIVERED':
            notificationMessage = 'Your order has been successfully delivered.';
            this.dialog.open(ReviewDialogComponent, {
              data: { orderId },
            });
            break;
          case 'UNSUCCESSFUL_DELIVERY':
            notificationMessage =
              'Unfortunately, your order has not been successfully delivered.';
            break;
          default:
            break;
        }
        this.store.dispatch(
          notifyInfo({
            message: notificationMessage,
            title,
          })
        );
        this.store.dispatch(getActiveOrders());
      }
    );
  }
}
