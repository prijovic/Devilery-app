import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, map, Subscription} from "rxjs";
import {RestaurantItem} from "../../../../shared/model/restaurant-item.model";
import {Store} from "@ngrx/store";
import {selectItems, selectRestaurantId} from "../../../store/ordering.selectors";
import {Address} from "../../../../shared/model/address.model";
import {AddressService} from "../../../../shared/services/address.service";
import {createOrder, getOrderChargeEstimation} from "../../../store/ordering.actions";
import {selectRestaurantById} from "../../../../restaurants/store/restaurants.selectors";

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.scss']
})
export class NewOrderComponent implements OnInit, OnDestroy {
  restaurantItems = new BehaviorSubject<RestaurantItem[]>([]);
  storeSubscription!: Subscription;
  restaurantAddress = new BehaviorSubject<Address | null>(null);
  restaurantIdSubscription!: Subscription;
  restaurantSubscription!: Subscription;
  minOrder = new BehaviorSubject<number>(0);
  selectedAddress!: Address;
  isOrderValid = new BehaviorSubject<boolean>(true);
  deliveryDistance = 0;

  constructor(private store: Store, private addressService: AddressService) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectItems)
      .pipe(
        map(items => Array.from(new Set(items)))
      ).subscribe(items => this.restaurantItems.next(items));
    this.restaurantIdSubscription = this.store.select(selectRestaurantId).subscribe(
      restaurantId => {
        if (restaurantId) {
          this.restaurantSubscription = this.store.select(selectRestaurantById(restaurantId)).subscribe(restaurant => {
            this.restaurantAddress.next(restaurant.address);
            this.minOrder.next(restaurant.minOrder);
          })
        }
      }
    );
    this.getOrderChargeEstimation();
  }

  ngOnDestroy() {
    this.storeSubscription?.unsubscribe();
    this.restaurantSubscription?.unsubscribe();
    this.restaurantIdSubscription?.unsubscribe();
  }

  onAddressSelected($event: Address) {
    this.selectedAddress = $event;
    this.getOrderChargeEstimation();
  }

  getOrderChargeEstimation() {
    const itemIds = this.restaurantItems.getValue().map(item => item.id);
    const restaurantAddress = this.restaurantAddress.getValue();
    if (restaurantAddress && this.selectedAddress) {
      this.addressService.getAddressDistance(restaurantAddress.longitude, restaurantAddress.latitude, this.selectedAddress.longitude, this.selectedAddress.latitude).subscribe(distance => {
        this.deliveryDistance = distance;
        this.store.dispatch(getOrderChargeEstimation({itemIds, deliveryDistance: distance}))
      });
    } else {
      this.store.dispatch(getOrderChargeEstimation({itemIds, deliveryDistance: 0}));
    }
  }

  determineValidityOfOrder($event: number) {
    this.isOrderValid.next($event >= this.minOrder.getValue());
  }

  createOrder() {
    if (this.selectedAddress && this.isOrderValid.getValue() && this.deliveryDistance) {
      this.store.dispatch(createOrder({addressId: this.selectedAddress.id, deliveryDistance: this.deliveryDistance}));
    }
  }
}
