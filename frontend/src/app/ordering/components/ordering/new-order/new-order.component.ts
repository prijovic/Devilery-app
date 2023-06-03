import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject , map, Subscription} from "rxjs";
import {RestaurantItem} from "../../../../shared/model/restaurant-item.model";
import {Store} from "@ngrx/store";
import {selectItems} from "../../../store/ordering.selectors";
import {Address} from "../../../../shared/model/address.model";
import {AddressService} from "../../../../shared/services/address.service";

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.scss']
})
export class NewOrderComponent implements OnInit, OnDestroy {
  restaurantItems = new BehaviorSubject<RestaurantItem[]>([]);
  storeSubscription!: Subscription;

  constructor(private store: Store, private addressService: AddressService) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectItems)
      .pipe(
        map(items => Array.from(new Set(items)))
      )
      .subscribe(items => this.restaurantItems.next(items));
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }

  onAddressSelected($event: Address) {

  }
}
