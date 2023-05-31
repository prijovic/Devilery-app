import { createAction, props, union } from '@ngrx/store';

export const login = createAction(
  '[Auth] Login Start',
  props<{ email: string; password: string }>()
);

export const loginSuccess = createAction(
  '[Auth] Login Success',
  props<{ token: string }>()
);

export const autoLogin = createAction('[Auth] Auto Login');

export const autoLoginFail = createAction('[Auth] Auto Login  Failed');

export const logout = createAction('[Auth] Logout');

export const logoutSuccess = createAction('[Auth] Logout Success');

export const signUp = createAction(
  '[Auth] Sign Up Start',
  props<{ email: string; password: string; name: string; surname: string; phoneNumber: string; profilePicture: string }>()
);

export const confirmEmail = createAction(
  '[Auth] Confirm Email',
  props<{ token: string }>()
);

export const confirmEmailSuccess = createAction('[Auth] Confirm Email Success');

const all = union({
  login,
  autoLogin,
  autoLoginFail,
  loginSuccess,
  logout,
  logoutSuccess,
  signUp,
  confirmEmail,
  confirmEmailSuccess,
});

export type AuthActionsUnion = typeof all;
