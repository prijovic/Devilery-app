import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { Dialog } from '@angular/cdk/dialog';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { DelivererOrder } from '../../../model/deliverer-order.model';
import { selectOrders } from '../../../store/deliverer-active-orders.selectors';
import { deliveringOrder } from '../../../store/deliverer-active-orders.actions';

@Component({
  selector: 'app-deliverer-active-orders',
  templateUrl: './deliverer-active-orders.component.html',
  styleUrls: ['./deliverer-active-orders.component.scss'],
})
export class DelivererActiveOrdersComponent implements OnInit, OnDestroy {
  private storeSubscription!: Subscription;
  pendingOrders: DelivererOrder[] = [];
  deliveringOrders: DelivererOrder[] = [];

  constructor(private store: Store, private dialog: Dialog) {}

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectOrders)
      .subscribe((orders) => {
        this.pendingOrders = orders.filter((order) => order.status === 'DONE');
        this.deliveringOrders = orders.filter(
          (order) => order.status === 'BEING_DELIVERED'
        );
      });
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }

  drop(event: CdkDragDrop<DelivererOrder[]>) {
    const order = event.container.data[0];
    if (event.distance.x > 0) {
      const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
        data: {
          title: 'Pick Up Order',
          text: `Did you pick up order ${order.id}?`,
          confirmationButtonText: 'Yes, I picked it',
          cancelButtonText: 'No, cancel',
        },
      });

      dialogRef.closed.subscribe((answer) => {
        if (answer === 'confirm') {
          this.store.dispatch(deliveringOrder({ id: order.id }));
        }
      });
    }
  }
}
