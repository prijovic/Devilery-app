import {createFeatureSelector, createSelector} from "@ngrx/store";
import {State} from "./ordering.reducer";

export const selectOrderingFeature = createFeatureSelector<State>('ordering');

export const selectItems = createSelector(
  selectOrderingFeature,
  (state: State) => state.items
);

export const selectRestaurantId = createSelector(
  selectOrderingFeature,
  (state: State) => state.restaurantId
);

export const selectNewOrderAttempt = createSelector(
  selectOrderingFeature,
  (state: State) => state.newOrderAttempt
);

export const selectOrderChargeEstimation = createSelector(
  selectOrderingFeature,
  (state: State) => state.orderChargeEstimation
);
