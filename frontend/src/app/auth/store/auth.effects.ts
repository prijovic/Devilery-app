import { Injectable } from '@angular/core';
import { AuthHttpService } from '../services/auth-http.service';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AuthActions from './auth.actions';
import * as CoreActions from '../../core/store/core.actions';
import { autoLoginFail, loginSuccess } from './auth.actions';
import {catchError, map, of, switchMap, tap} from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthEffects {
  login = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.login.type),
      switchMap((action) => {
        return this.httpService
          .sendLoginRequest(action.email, action.password)
          .pipe(
            map((authToken) =>
              AuthActions.loginSuccess({ token: authToken.token })
            )
          );
      })
    );
  });

  autoLogin = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.autoLogin.type),
      map(() => {
        const token = sessionStorage.getItem('token');
        if (!token) {
          return autoLoginFail();
        }
        return loginSuccess({ token });
      })
    )
  );

  login_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(AuthActions.loginSuccess.type),
        map((action) => {
          sessionStorage.setItem('token', action.token);
          this.authService.setLogoutTimer();
          this.router.navigate(['/']);
        })
      );
    },
    { dispatch: false }
  );

  logout = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.logout.type),
      map(() => {
        sessionStorage.clear();
        this.authService.clearLogoutTimer();
        return AuthActions.logoutSuccess();
      })
    );
  });

  sign_up = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.signUp.type),
      switchMap((action) => {
        return this.httpService
          .sendSignUpRequest(action.email, action.password, action.name, action.surname, action.phoneNumber, action.profilePicture)
          .pipe(
            map(() => CoreActions.notifySuccess({message: 'Your sign up request has been sent. Please go check your email.', title: 'Sign Up'})),
            catchError(() => of(CoreActions.cleanUpFile({fileName: action.profilePicture})))
          );
      })
    );
  });

  confirm_email = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.confirmEmail.type),
      switchMap((action) => {
        return this.httpService
          .sendEmailConfirmationRequest(action.token)
          .pipe(map(() => AuthActions.confirmEmailSuccess()));
      })
    );
  });

  confirm_email_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(AuthActions.confirmEmailSuccess.type),
        tap(() => this.router.navigate(['/auth/login'])),
        map(() => CoreActions.notifySuccess({message: 'You have successfully confirmed your email address. You may log in now.', title: 'Email Confirmation'})),
      );
    }
  );

  constructor(
    private router: Router,
    private actions$: Actions<AuthActions.AuthActionsUnion>,
    private httpService: AuthHttpService,
    private authService: AuthService
  ) {}
}
