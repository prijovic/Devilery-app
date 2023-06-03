import {Action, createReducer, on} from "@ngrx/store";
import {Restaurant} from "../model/restaurant.model";
import * as RestaurantsActions from './restaurants.actions';
import {RestaurantItem} from "../../shared/model/restaurant-item.model";

export interface State {
  restaurants: Restaurant[];
  restaurantItems: RestaurantItem[];
}

const initialState: State = {
  restaurants: [],
  restaurantItems: []
};

const restaurantsReducer = createReducer(
  initialState,
  on(RestaurantsActions.setRestaurants, (state, { restaurants }) => ({
  ...state,
  restaurants
  })),
  on(RestaurantsActions.setRestaurantMenuItems, (state, { restaurantItems }) => ({
    ...state,
    restaurantItems
  })),
);

export function reducer(state: State | undefined, action: Action) {
  return restaurantsReducer(state, action);
}
