import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './restaurants.reducer';
import { Restaurant } from '../../shared/model/restaurant.model';

export const selectRestaurantsFeature =
  createFeatureSelector<State>('restaurants');

export const selectRestaurants = createSelector(
  selectRestaurantsFeature,
  (state: State) => state.restaurants
);

export const selectRestaurantItems = createSelector(
  selectRestaurantsFeature,
  (state: State) => state.restaurantItems
);

export const selectRestaurantById = (id: string) =>
  createSelector(
    selectRestaurants,
    (restaurants: Restaurant[]) =>
      restaurants.filter((restaurant) => restaurant.id === id)[0]
  );
