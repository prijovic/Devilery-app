import {Component, Inject} from '@angular/core';
import * as OrderingActions from "../../../../../../ordering/store/ordering.actions";
import {Store} from "@ngrx/store";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {RestaurantItem} from "../../../../../../shared/model/restaurant-item.model";

@Component({
  selector: 'app-new-order-attempt-dialog',
  templateUrl: './new-order-attempt-dialog.component.html',
  styleUrls: ['./new-order-attempt-dialog.component.scss']
})
export class NewOrderAttemptDialogComponent {

  constructor(private store: Store, @Inject(MAT_DIALOG_DATA)
  private data: {
    restaurantId: string;
    item: RestaurantItem;
  }) {}

  newOrderAttemptAccepted() {
    this.store.dispatch(OrderingActions.newOrderAttemptAccepted({restaurantId: this.data.restaurantId, item: this.data.item}))
  }

  newOrderAttemptRejected() {
    this.store.dispatch(OrderingActions.newOrderAttemptRejected());
  }
}
