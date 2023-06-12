import { createAction, props, union } from '@ngrx/store';
import { RestaurantOrder } from '../model/restaurant-order.model';

export const getRestaurantId = createAction(
  '[Restaurant Active Orders] Get Restaurant Id'
);

export const setRestaurantId = createAction(
  '[Restaurant Active Orders] Set Restaurant Id',
  props<{ id: string }>()
);

export const getActiveRestaurantOrders = createAction(
  '[Restaurant Active Orders] Get Active Restaurant Orders'
);

export const getRestaurantOrderById = createAction(
  '[Restaurant Active Orders] Get Restaurant Order By Id',
  props<{ id: string }>()
);

export const setOrder = createAction(
  '[Restaurant Active Orders] Set Order',
  props<{ order: RestaurantOrder }>()
);

export const setActiveRestaurantOrders = createAction(
  '[Restaurant Active Orders] Set Active Restaurant Orders',
  props<{ restaurantOrders: RestaurantOrder[] }>()
);

export const acceptOrder = createAction(
  '[Restaurant Active Orders] Accept Order',
  props<{ id: string }>()
);

export const rejectOrder = createAction(
  '[Restaurant Active Orders] Reject Order',
  props<{ id: string; reason: string }>()
);

export const finishOrder = createAction(
  '[Restaurant Active Orders] Finish Order',
  props<{ id: string }>()
);

export const orderStatusChanged = createAction(
  '[Restaurant Active Orders] Order Status Changed',
  props<{ id: string; status: string }>()
);

export const removeOrder = createAction(
  '[Restaurant Active Orders] Remove Order',
  props<{ id: string }>()
);

const all = union({
  getRestaurantId,
  setRestaurantId,
  getRestaurantOrderById,
  setOrder,
  getActiveRestaurantOrders,
  setActiveRestaurantOrders,
  acceptOrder,
  rejectOrder,
  finishOrder,
  orderStatusChanged,
  removeOrder,
});

export type RestaurantActiveOrdersActionsUnion = typeof all;
