import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Restaurant } from '../../../../../shared/model/restaurant.model';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectRestaurants } from '../../../../store/restaurants.selectors';

@Component({
  selector: 'app-search-result-restaurants',
  templateUrl: './search-result-restaurants.component.html',
  styleUrls: ['./search-result-restaurants.component.scss'],
})
export class SearchResultRestaurantsComponent implements OnInit, OnDestroy {
  restaurants: Restaurant[] = [];
  type = '';
  storeSubscription!: Subscription;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => (this.type = params['type']));
    this.storeSubscription = this.store
      .select(selectRestaurants)
      .subscribe((restaurants) => (this.restaurants = restaurants));
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }
}
