import { Component, Input, OnInit } from '@angular/core';
import { RestaurantOrder } from '../../../../model/restaurant-order.model';
import { Store } from '@ngrx/store';
import { Dialog } from '@angular/cdk/dialog';
import { ConfirmationDialogComponent } from '../../../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { ConfirmationDialogWithReasonInputComponent } from '../../../../../shared/components/confirmation-dialog-with-reason-input/confirmation-dialog-with-reason-input.component';
import {
  acceptOrder,
  rejectOrder,
} from 'src/app/restaurant-active-orders/store/restaurant-active-orders.actions';

@Component({
  selector: 'app-order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.scss'],
})
export class OrderCardComponent implements OnInit {
  @Input() order!: RestaurantOrder;
  itemCounts: { item: string; count: number }[] = [];
  isPending = false;

  constructor(private store: Store, private dialog: Dialog) {}

  ngOnInit() {
    const itemCountsMap = new Map<string, { item: string; count: number }>();

    for (const item of this.order.items) {
      const existingCount = itemCountsMap.get(item.id);
      if (existingCount) {
        existingCount.count++;
      } else {
        itemCountsMap.set(item.id, { item: item.name, count: 1 });
      }
    }

    this.itemCounts = Array.from(itemCountsMap.values());
    this.isPending = this.order.status === 'PENDING';
  }

  acceptOrder() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Accept Order',
        text: `Are you sure you want to accept order ${this.order.id}?`,
        confirmationButtonText: 'Yes, accept it',
        cancelButtonText: 'No, cancel',
      },
    });

    dialogRef.closed.subscribe((answer) => {
      if (answer === 'confirm') {
        this.store.dispatch(acceptOrder({ id: this.order.id }));
      }
    });
  }

  rejectOrder() {
    const dialogRef = this.dialog.open(
      ConfirmationDialogWithReasonInputComponent,
      {
        data: {
          title: 'Reject Order',
          text: `Are you sure you want to reject order ${this.order.id}?`,
          confirmationButtonText: 'Yes, reject it',
          cancelButtonText: 'No, cancel',
        },
      }
    );

    dialogRef.closed.subscribe((answer) => {
      if (typeof answer === 'string') {
        this.store.dispatch(rejectOrder({ id: this.order.id, reason: answer }));
      }
    });
  }
}
