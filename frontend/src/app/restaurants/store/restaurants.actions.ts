import {createAction, props, union} from "@ngrx/store";
import {Restaurant} from "../model/restaurant.model";
import {RestaurantItem} from "../model/restaurant-item.model";

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

export const getRestaurantMenuItems = createAction(
  '[Restaurants] Get Restaurant Items',
  props<{restaurantId: string}>()
);

export const setRestaurantMenuItems = createAction(
  '[Restaurants] Set Restaurant Items',
  props<{restaurantItems: RestaurantItem[]}>()
);

const all = union({
  getRestaurants,
  setRestaurants,
  getRestaurantsByType,
  getRestaurantMenuItems,
  setRestaurantMenuItems
});

export type RestaurantsActionsUnion = typeof all;
