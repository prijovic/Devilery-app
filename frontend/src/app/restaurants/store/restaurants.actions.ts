import { createAction, props, union } from '@ngrx/store';
import { Restaurant } from '../../shared/model/restaurant.model';
import { RestaurantItem } from '../../shared/model/restaurant-item.model';

export const getRestaurants = createAction('[Restaurants] Get Restaurants');

export const setRestaurants = createAction(
  '[Restaurants] Set Restaurants',
  props<{ restaurants: Restaurant[] }>()
);

export const getRestaurantsByType = createAction(
  '[Restaurants] Get Restaurants By Type',
  props<{ restaurantType: string }>()
);

export const getRestaurantsByText = createAction(
  '[Restaurants] Get Restaurants By Text',
  props<{ text: string }>()
);

export const getRestaurantMenuItems = createAction(
  '[Restaurants] Get Restaurant Items',
  props<{ restaurantId: string }>()
);

export const setRestaurantMenuItems = createAction(
  '[Restaurants] Set Restaurant Items',
  props<{ restaurantItems: RestaurantItem[] }>()
);

const all = union({
  getRestaurants,
  setRestaurants,
  getRestaurantsByType,
  getRestaurantsByText,
  getRestaurantMenuItems,
  setRestaurantMenuItems,
});

export type RestaurantsActionsUnion = typeof all;
