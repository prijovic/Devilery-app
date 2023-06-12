import { Action, createReducer, on } from '@ngrx/store';
import { RestaurantOrder } from '../model/restaurant-order.model';
import * as ActiveRestaurantOrdersActions from './restaurant-active-orders.actions';

export interface State {
  orders: RestaurantOrder[];
  restaurantId: string | null;
}

const initialState: State = {
  orders: [],
  restaurantId: null,
};

const restaurantActiveOrdersReducer = createReducer(
  initialState,
  on(ActiveRestaurantOrdersActions.setOrder, (state, { order }) => ({
    ...state,
    orders: [...state.orders, order],
  })),
  on(
    ActiveRestaurantOrdersActions.setActiveRestaurantOrders,
    (state, { restaurantOrders }) => ({
      ...state,
      orders: restaurantOrders,
    })
  ),
  on(ActiveRestaurantOrdersActions.setRestaurantId, (state, { id }) => ({
    ...state,
    restaurantId: id,
  })),
  on(
    ActiveRestaurantOrdersActions.orderStatusChanged,
    (state, { id, status }) => ({
      ...state,
      orders: state.orders.map((order) => {
        if (order.id === id) {
          return {
            ...order,
            status: status,
          };
        }
        return order;
      }),
    })
  ),
  on(ActiveRestaurantOrdersActions.removeOrder, (state, { id }) => ({
    ...state,
    orders: state.orders.filter((order) => order.id !== id),
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return restaurantActiveOrdersReducer(state, action);
}
