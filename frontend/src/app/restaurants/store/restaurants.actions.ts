import {createAction, props, union} from "@ngrx/store";
import {Restaurant} from "../model/restaurant.model";

export const getRestaurants = createAction(
  '[Restaurants] Get Restaurants'
);

export const setRestaurants = createAction(
  '[Restaurants] Set Restaurants',
  props<{restaurants: Restaurant[]}>()
);

export const getRestaurantsByType = createAction(
  '[Restaurants] Get Restaurants By Type',
  props<{restaurantType: string}>()
);

const all = union({
  getRestaurants,
  setRestaurants,
  getRestaurantsByType
});

export type RestaurantsActionsUnion = typeof all;
