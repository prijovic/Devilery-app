import {createAction, props, union} from "@ngrx/store";
import {Restaurant} from "../model/restaurant.model";

export const getRestaurants = createAction(
  '[Restaurants] Get Restaurants'
);

export const setRestaurants = createAction(
  '[Restaurants] Set Restaurants',
  props<{restaurants: Restaurant[]}>()
);

const all = union({
  getRestaurants,
  setRestaurants
});

export type RestaurantsActionsUnion = typeof all;
