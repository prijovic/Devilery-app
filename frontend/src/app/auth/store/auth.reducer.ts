import { Action, createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';

export interface State {
  token: string | null;
}

const initialState: State = {
  token: null,
};

const authReducer = createReducer(
  initialState,
  on(AuthActions.loginSuccess, (state, { token }) => ({
    ...state,
    token: token,
  })),
  on(AuthActions.logoutSuccess, (state) => ({
    ...state,
    token: null,
  })),
  on(AuthActions.autoLoginFail, (state) => ({
    ...state,
    token: null,
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
