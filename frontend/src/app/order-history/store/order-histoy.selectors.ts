import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './order-history.reducer';

export const selectOrderHistoryFeature =
  createFeatureSelector<State>('order-history');

export const selectOrders = createSelector(
  selectOrderHistoryFeature,
  (state) => state.orders
);

export const selectOrderById = (id: string) =>
  createSelector(
    selectOrderHistoryFeature,
    (state) => state.orders.filter((order) => order.id === id)[0]
  );
