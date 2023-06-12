import { createAction, props, union } from '@ngrx/store';
import { RestaurantItem } from '../../shared/model/restaurant-item.model';
import { Charge } from '../../shared/model/charge.model';
import { Address } from '../../shared/model/address.model';
import { Order } from '../../shared/model/order.model';

export const addItemToOrder = createAction(
  '[Ordering] Add Item To Order',
  props<{ item: RestaurantItem; restaurantId: string }>()
);

export const askForNewOrderAttempt = createAction(
  '[Ordering] Ask For New Order Attempt',
  props<{ item: RestaurantItem; restaurantId: string }>()
);

export const newOrderAttemptAccepted = createAction(
  '[Ordering] New Order Attempt Accepted',
  props<{
    item: RestaurantItem;
    restaurantId: string;
  }>()
);

export const newOrderAttemptRejected = createAction(
  '[Ordering] New Order Attempt Rejected'
);

export const removeItemFromOrder = createAction(
  '[Ordering] Remove Item From Order',
  props<{ itemId: string }>()
);

export const getOrderChargeEstimation = createAction(
  '[Ordering] Get Order Charge Estimation',
  props<{ itemIds: string[]; deliveryDistance: number }>()
);

export const setOrderChargeEstimation = createAction(
  '[Ordering] Set Order Charge Estimation',
  props<{ orderChargeEstimation: Charge }>()
);

export const setSelectedAddress = createAction(
  '[Ordering] Set Selected Address',
  props<{ selectedAddress: Address }>()
);

export const createOrder = createAction(
  '[Ordering] Create Order',
  props<{ addressId: string; deliveryDistance: number }>()
);

export const createOrderSuccess = createAction(
  '[Ordering] Create Order Success'
);

export const getActiveOrders = createAction('[Ordering] Get Active Orders');

export const setActiveOrders = createAction(
  '[Ordering] Set Active Orders',
  props<{ orders: Order[] }>()
);

export const submitReview = createAction(
  '[Ordering] Submit Review',
  props<{
    orderId: string;
    delivererRating: number;
    delivererComment: string;
    restaurantRating: number;
    restaurantComment: string;
  }>()
);

const all = union({
  addItemToOrder,
  askForNewOrderAttempt,
  newOrderAttemptAccepted,
  newOrderAttemptRejected,
  removeItemFromOrder,
  getOrderChargeEstimation,
  setOrderChargeEstimation,
  setSelectedAddress,
  createOrder,
  createOrderSuccess,
  getActiveOrders,
  setActiveOrders,
  submitReview,
});

export type OrderingActionsUnion = typeof all;
