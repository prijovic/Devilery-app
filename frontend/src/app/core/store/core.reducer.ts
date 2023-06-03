import { ActionReducerMap } from '@ngrx/store';
import * as fromAuth from '../../auth/store/auth.reducer';
import * as fromRestaurants from '../../restaurants/store/restaurants.reducer';
import * as fromOrdering from '../../ordering/store/ordering.reducer';

interface State {
  auth: fromAuth.State;
  restaurants: fromRestaurants.State;
  ordering: fromOrdering.State;
}

export const reducers: ActionReducerMap<State> = {
  auth: fromAuth.reducer,
  restaurants: fromRestaurants.reducer,
  ordering: fromOrdering.reducer
};
