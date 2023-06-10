import { ActionReducerMap } from '@ngrx/store';
import * as fromAuth from '../../auth/store/auth.reducer';
import * as fromRestaurants from '../../restaurants/store/restaurants.reducer';
import * as fromOrdering from '../../ordering/store/ordering.reducer';
import * as fromRestaurantActiveOrders from '../../restaurant-active-orders/store/restaurant-active-orders.reducer';
import * as fromDelivererActiveOrders from '../../deliverer-active-orders/store/deliverer-active-orders.reducer';

interface State {
  auth: fromAuth.State;
  restaurants: fromRestaurants.State;
  ordering: fromOrdering.State;
  'restaurants-active-orders': fromRestaurantActiveOrders.State;
  'deliverer-active-orders': fromDelivererActiveOrders.State;
}

export const reducers: ActionReducerMap<State> = {
  auth: fromAuth.reducer,
  restaurants: fromRestaurants.reducer,
  ordering: fromOrdering.reducer,
  'restaurants-active-orders': fromRestaurantActiveOrders.reducer,
  'deliverer-active-orders': fromDelivererActiveOrders.reducer,
};
