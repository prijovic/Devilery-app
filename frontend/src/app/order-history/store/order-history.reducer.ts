import { Order } from '../../shared/model/order.model';
import { Action, createReducer, on } from '@ngrx/store';
import * as OrderHistoryActions from './order-history.actions';

export interface State {
  orders: Order[];
}

const initialState: State = {
  orders: [],
};

const orderHistoryReducer = createReducer(
  initialState,
  on(OrderHistoryActions.setOrderHistory, (state, { orders }) => ({
    ...state,
    orders,
  })),
  on(OrderHistoryActions.reportSubmitted, (state, { orderId }) => {
    const updatedOrders = state.orders.map((order) => {
      if (order.id === orderId) {
        return {
          ...order,
          isReportSubmitted: true,
        };
      }
      return order;
    });

    return {
      ...state,
      orders: updatedOrders,
    };
  })
);

export function reducer(state: State | undefined, action: Action) {
  return orderHistoryReducer(state, action);
}
