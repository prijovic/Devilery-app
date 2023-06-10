import { Component, OnDestroy, OnInit } from '@angular/core';
import { Restaurant } from '../../../../../shared/model/restaurant.model';
import { Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectRestaurants } from '../../../../store/restaurants.selectors';

@Component({
  selector: 'app-recommended-restaurants',
  templateUrl: './recommended-restaurants.component.html',
  styleUrls: ['./recommended-restaurants.component.scss'],
})
export class RecommendedRestaurantsComponent implements OnInit, OnDestroy {
  restaurants: Restaurant[] = [];
  storeSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectRestaurants)
      .subscribe((restaurants) => (this.restaurants = restaurants));
  }
}
