import { ActionReducerMap } from '@ngrx/store';
import * as fromAuth from '../../auth/store/auth.reducer';
import * as fromRestaurants from '../../restaurants/store/restaurants.reducer';

interface State {
  auth: fromAuth.State;
  restaurants: fromRestaurants.State;
}

export const reducers: ActionReducerMap<State> = {
  auth: fromAuth.reducer,
  restaurants: fromRestaurants.reducer
};
