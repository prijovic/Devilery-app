import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './deliverer-active-orders.reducer';

export const selectDelivererActiveOrdersFeature = createFeatureSelector<State>(
  'deliverer-active-orders'
);

export const selectOrders = createSelector(
  selectDelivererActiveOrdersFeature,
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
