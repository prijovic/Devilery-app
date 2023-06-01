import {Action, createReducer, on} from "@ngrx/store";
import {Restaurant} from "../model/restaurant.model";
import * as RestaurantsActions from './restaurants.actions';

export interface State {
  restaurants: Restaurant[];
}

const initialState: State = {
  restaurants: [],
};

const restaurantsReducer = createReducer(
  initialState,
  on(RestaurantsActions.setRestaurants, (state, { restaurants }) => ({
  ...state,
  restaurants
})),
);

export function reducer(state: State | undefined, action: Action) {
  return restaurantsReducer(state, action);
}
