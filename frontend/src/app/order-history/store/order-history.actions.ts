import { createAction, props, union } from '@ngrx/store';
import { Order } from '../../shared/model/order.model';

export const getOrderHistory = createAction(
  '[Order History] Get Order History'
);

export const setOrderHistory = createAction(
  '[Order History] Set Order History',
  props<{ orders: Order[] }>()
);

export const submitReport = createAction(
  '[Order History] Submit Report',
  props<{
    orderId: string;
    comment: string;
  }>()
);

export const reportSubmitted = createAction(
  '[Order History] Report Submitted',
  props<{ orderId: string }>()
);

const all = union({
  getOrderHistory,
  setOrderHistory,
  submitReport,
  reportSubmitted,
});

export type OrderHistoryActionsUnion = typeof all;
