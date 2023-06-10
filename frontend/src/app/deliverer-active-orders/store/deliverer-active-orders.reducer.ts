import { Action, createReducer, on } from '@ngrx/store';
import * as DelivererActiveOrdersActions from '../store/deliverer-active-orders.actions';
import { DelivererOrder } from '../model/deliverer-order.model';

export interface State {
  orders: DelivererOrder[];
}

const initialState: State = {
  orders: [],
};

const delivererActiveOrdersReducer = createReducer(
  initialState,
  on(DelivererActiveOrdersActions.setOrder, (state, { order }) => ({
    ...state,
    orders: [...state.orders, order],
  })),
  on(
    DelivererActiveOrdersActions.setActiveDelivererOrders,
    (state, { orders }) => ({
      ...state,
      orders,
    })
  ),
  on(
    DelivererActiveOrdersActions.orderStatusChanged,
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
  )
);

export function reducer(state: State | undefined, action: Action) {
  return delivererActiveOrdersReducer(state, action);
}
