import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectOrders } from '../../../store/restaurant-active-orders.selectors';
import { RestaurantOrder } from '../../../model/restaurant-order.model';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { Dialog } from '@angular/cdk/dialog';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import {
  finishOrder,
  removeOrder,
} from '../../../store/restaurant-active-orders.actions';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Component({
  selector: 'app-restaurant-active-orders',
  templateUrl: './restaurant-active-orders.component.html',
  styleUrls: ['./restaurant-active-orders.component.scss'],
})
export class RestaurantActiveOrdersComponent implements OnInit, OnDestroy {
  private storeSubscription!: Subscription;
  private stompClient: any;
  pendingOrders: RestaurantOrder[] = [];
  acceptedOrders: RestaurantOrder[] = [];
  doneOrders: RestaurantOrder[] = [];

  constructor(private store: Store, private dialog: Dialog) {}

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectOrders)
      .subscribe((orders) => {
        this.pendingOrders = orders.filter(
          (order) => order.status === 'PENDING'
        );
        this.acceptedOrders = orders.filter(
          (order) => order.status === 'ACCEPTED'
        );
        this.doneOrders = orders.filter((order) => order.status === 'DONE');
      });
    this.initializeWebSocketConnection();
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
    this.stompClient.disconnect();
  }

  initializeWebSocketConnection() {
    const ws = new SockJS('http://localhost:8080/api/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    this.stompClient.connect({}, () => {
      this.openOrderStatusSocket();
    });
  }

  openOrderStatusSocket() {
    this.stompClient.subscribe(
      '/topic/order-picked-up',
      (message: { body: string }) => {
        this.store.dispatch(removeOrder({ id: message.body }));
      }
    );
  }

  drop(event: CdkDragDrop<RestaurantOrder[]>) {
    const order = event.container.data[0];
    if (event.distance.x > 0) {
      const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
        data: {
          title: 'Finish Order',
          text: `Are you sure you want to finish order ${order.id}?`,
          confirmationButtonText: 'Yes, finish it',
          cancelButtonText: 'No, cancel',
        },
      });

      dialogRef.closed.subscribe((answer) => {
        if (answer === 'confirm') {
          this.store.dispatch(finishOrder({ id: order.id }));
        }
      });
    }
  }
}
