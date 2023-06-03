import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {RestaurantItem} from "../../../../../shared/model/restaurant-item.model";
import {BehaviorSubject, Subscription} from "rxjs";
import {Store} from "@ngrx/store";
import {selectToken} from "../../../../../auth/store/auth.selectors";
import {selectNewOrderAttempt} from "../../../../../ordering/store/ordering.selectors";
import {
  NewOrderAttemptDialogComponent
} from "./new-order-attempt-dialog/new-order-attempt-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-restaurant-items-container',
  templateUrl: './restaurant-items-container.component.html',
  styleUrls: ['./restaurant-items-container.component.scss']
})
export class RestaurantItemsContainerComponent implements OnInit, OnDestroy {
  @Input() restaurantItems!: RestaurantItem[];
  @Input() restaurantId!: string;
  isUserLoggedIn = new BehaviorSubject<boolean>(false);
  tokenSubscription!: Subscription;
  newOrderAttemptSubscription!: Subscription;
  lastAddedRestaurantItem!: RestaurantItem;

  constructor(private store: Store, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.tokenSubscription = this.store.select(selectToken).subscribe(token => this.isUserLoggedIn.next(!!token));
    this.newOrderAttemptSubscription = this.store.select(selectNewOrderAttempt).subscribe(newOrderAttempt => {
      if (newOrderAttempt && this.lastAddedRestaurantItem) {
        this.dialog.open(NewOrderAttemptDialogComponent, {
          data: {
            restaurantId: this.restaurantId,
            item: this.lastAddedRestaurantItem
          }
        })
      }
    });
  }

  ngOnDestroy() {
    this.tokenSubscription.unsubscribe();
    this.newOrderAttemptSubscription.unsubscribe();
  }

  setLastAddedItem($event: RestaurantItem) {
    this.lastAddedRestaurantItem = $event
  }
}
