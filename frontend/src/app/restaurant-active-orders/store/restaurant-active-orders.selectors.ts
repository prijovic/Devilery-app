import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './restaurant-active-orders.reducer';

export const selectRestaurantActiveOrdersFeature = createFeatureSelector<State>(
  'restaurant-active-orders'
);

export const selectRestaurantId = createSelector(
  selectRestaurantActiveOrdersFeature,
  (state) => state.restaurantId
);

export const selectOrders = createSelector(
  selectRestaurantActiveOrdersFeature,
  (state) => state.orders
);

export const selectOrderById = (id: string) =>
  createSelector(
    selectOrders,
    (orders) => orders.filter((order) => order.id === id)[0]
  );

export const selectOrdersWithStatus = (status: string) =>
  createSelector(selectOrders, (orders) =>
    orders.filter(
      (order) => order.status.toLowerCase() === status.toLowerCase()
    )
  );
