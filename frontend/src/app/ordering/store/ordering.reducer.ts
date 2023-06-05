import {Action, createReducer, on} from "@ngrx/store";
import * as OrderingActions from './ordering.actions';
import {RestaurantItem} from "../../shared/model/restaurant-item.model";
import {Charge} from "../../shared/model/charge.model";

export interface State {
  items: RestaurantItem[];
  restaurantId: string | null;
  newOrderAttempt: boolean;
  orderChargeEstimation: Charge | null;
}

const initialState: State = {
  items: [],
  restaurantId: null,
  newOrderAttempt: false,
  orderChargeEstimation: null
};

const orderingReducer = createReducer(
  initialState,
  on(OrderingActions.addItemToOrder, (state, { item , restaurantId }) => {
    return {
      ...state,
      items: state.restaurantId !== null && restaurantId !== state.restaurantId ? state.items : [...state.items, item],
      restaurantId: state.restaurantId != null && restaurantId !== state.restaurantId ? state.restaurantId : restaurantId
    };
  }),
  on(OrderingActions.askForNewOrderAttempt, (state) => ({...state, newOrderAttempt: true})),
  on(OrderingActions.newOrderAttemptAccepted, (state, {item, restaurantId}) => {
    return {...state, restaurantId, items: [item], newOrderAttempt: false};
  }),
  on(OrderingActions.newOrderAttemptRejected, (state) => ({...state, newOrderAttempt: false})),
  on(OrderingActions.removeItemFromOrder, (state, { itemId }) => {
    const itemIndex = state.items.findIndex(item => item.id === itemId);
    if (itemIndex > -1) {
      const updatedItems = [...state.items];
      updatedItems.splice(itemIndex, 1);

      return {
        ...state,
        items: updatedItems,
        restaurantId: updatedItems.length === 0 ? null : state.restaurantId
      };
    }

    return state;
  }),
  on(OrderingActions.setOrderChargeEstimation, (state, {orderChargeEstimation}) => ({
    ...state,
    orderChargeEstimation
  })),
  on(OrderingActions.createOrderSuccess, (state) => ({...state, orderChargeEstimation: null, restaurantId: null, items: []}))
);

export function reducer(state: State | undefined, action: Action) {
  return orderingReducer(state, action);
}
