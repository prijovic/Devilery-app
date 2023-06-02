import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Subscription} from "rxjs";
import {Store} from "@ngrx/store";
import {selectRestaurantById, selectRestaurantItems} from "../../../store/restaurants.selectors";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-restaurant-profile',
  templateUrl: './restaurant-profile.component.html',
  styleUrls: ['./restaurant-profile.component.scss']
})
export class RestaurantProfileComponent implements OnInit, OnDestroy {
  id = "";
  restaurant: BehaviorSubject<any> = new BehaviorSubject(null);
  restaurantItems: BehaviorSubject<any> = new BehaviorSubject(null);
  restaurantSubscription!: Subscription;
  menuItemsSubscription!: Subscription;

  constructor(private route: ActivatedRoute, private store: Store) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.id = params['id']);
    this.restaurantSubscription = this.store.select(selectRestaurantById(this.id)).subscribe(restaurant => this.restaurant.next(restaurant));
    this.menuItemsSubscription = this.store.select(selectRestaurantItems).subscribe(restaurantItems => this.restaurantItems.next(restaurantItems));
  }

  ngOnDestroy(): void {
    this.restaurantSubscription.unsubscribe();
    this.menuItemsSubscription.unsubscribe();
  }
}
