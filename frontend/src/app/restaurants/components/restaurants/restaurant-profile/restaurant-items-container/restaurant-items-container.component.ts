import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {RestaurantItem} from "../../../../model/restaurant-item.model";
import {BehaviorSubject, Subscription} from "rxjs";
import {Store} from "@ngrx/store";
import {selectToken} from "../../../../../auth/store/auth.selectors";

@Component({
  selector: 'app-restaurant-items-container',
  templateUrl: './restaurant-items-container.component.html',
  styleUrls: ['./restaurant-items-container.component.scss']
})
export class RestaurantItemsContainerComponent implements OnInit, OnDestroy {
  @Input() restaurantItems!: RestaurantItem[];
  isUserLoggedIn = new BehaviorSubject<boolean>(false);
  storeSubscription!: Subscription;

  constructor(private store: Store) {
  }

  ngOnInit() {
    this.storeSubscription = this.store.select(selectToken).subscribe(token => this.isUserLoggedIn.next(!!token));
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }
}
