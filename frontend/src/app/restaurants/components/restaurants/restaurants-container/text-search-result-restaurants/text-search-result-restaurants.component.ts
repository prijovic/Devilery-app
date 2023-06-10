import { Component, OnDestroy, OnInit } from '@angular/core';
import { Restaurant } from '../../../../../shared/model/restaurant.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectRestaurants } from '../../../../store/restaurants.selectors';

@Component({
  selector: 'app-text-search-result-restaurants',
  templateUrl: './text-search-result-restaurants.component.html',
  styleUrls: ['./text-search-result-restaurants.component.scss'],
})
export class TextSearchResultRestaurantsComponent implements OnInit, OnDestroy {
  restaurants: Restaurant[] = [];
  text = '';
  storeSubscription!: Subscription;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit(): void {
    this.route.params.subscribe(
      (params) => (this.text = params['text'].split('_').join(' '))
    );
    this.storeSubscription = this.store
      .select(selectRestaurants)
      .subscribe((restaurants) => (this.restaurants = restaurants));
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }
}
