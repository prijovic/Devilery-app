import {createAction, props, union} from "@ngrx/store";
import {RestaurantItem} from "../../shared/model/restaurant-item.model";

export const addItemToOrder = createAction(
  '[Ordering] Add Item To Order',
  props<{item: RestaurantItem, restaurantId: string}>()
);

export const askForNewOrderAttempt = createAction('[Ordering] Ask For New Order Attempt',
  props<{item: RestaurantItem, restaurantId: string}>()
);

export const newOrderAttemptAccepted = createAction('[Ordering] New Order Attempt Accepted', props<{item: RestaurantItem, restaurantId: string}>());

export const newOrderAttemptRejected = createAction('[Ordering] New Order Attempt Rejected');

export const removeItemFromOrder = createAction(
  '[Ordering] Remove Item From Order',
  props<{itemId: string}>()
);

const all = union({
  addItemToOrder,
  askForNewOrderAttempt,
  newOrderAttemptAccepted,
  newOrderAttemptRejected,
  removeItemFromOrder
});

export type OrderingActionsUnion = typeof all;
