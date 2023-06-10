import { createAction, props, union } from '@ngrx/store';
import { DelivererOrder } from '../model/deliverer-order.model';

export const getActiveDelivererOrders = createAction(
  '[Deliverer Active Orders] Get Active Deliverer Orders'
);

export const getDelivererOrderById = createAction(
  '[Deliverer Active Orders] Get Deliverer Order By Id',
  props<{ id: string }>()
);

export const setOrder = createAction(
  '[Deliverer Active Orders] Set Order',
  props<{ order: DelivererOrder }>()
);

export const setActiveDelivererOrders = createAction(
  '[Deliverer Active Orders] Set Active Deliverer Orders',
  props<{ orders: DelivererOrder[] }>()
);

export const successOrder = createAction(
  '[Deliverer Active Orders] Success Order',
  props<{ id: string }>()
);

export const failOrder = createAction(
  '[Deliverer Active Orders] Fail Order',
  props<{ id: string; reason: string }>()
);

export const deliveringOrder = createAction(
  '[Deliverer Active Orders] Delivering Order',
  props<{ id: string }>()
);

export const orderStatusChanged = createAction(
  '[Deliverer Active Orders] Order Status Changed',
  props<{ id: string; status: string }>()
);

const all = union({
  getActiveDelivererOrders,
  getDelivererOrderById,
  setOrder,
  setActiveDelivererOrders,
  successOrder,
  failOrder,
  deliveringOrder,
  orderStatusChanged,
});

export type DelivererActiveOrdersActionsUnion = typeof all;
