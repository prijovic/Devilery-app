import {createFeatureSelector, createSelector} from "@ngrx/store";
import {State} from "./restaurants.reducer";

export const selectRestaurantsFeature = createFeatureSelector<State>('restaurants');

export const selectRestaurants = createSelector(
  selectRestaurantsFeature,
  (state: State) => state.restaurants
);
