import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthToken } from '../model/auth-token.model';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';

@Injectable({
  providedIn: 'root',
})
export class AuthHttpService {
  private LOGIN = 'auth/login';
  private SIGN_UP = 'auth/register';
  private EMAIL_CONFIRMATION = 'auth/activateEmail/';
  private LOGOUT = 'auth/logout';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  sendLoginRequest(email: string, password: string) {
    return this.http.post<AuthToken>(this.config.apiEndpoint + this.LOGIN, {
      email,
      password,
    });
  }

  sendSignUpRequest(
    email: string,
    password: string,
    name: string,
    surname: string,
    phoneNumber: string,
    profilePicture: string
  ) {
    return this.http.post(this.config.apiEndpoint + this.SIGN_UP, {
      email,
      password,
      name,
      surname,
      phoneNumber,
      profilePicture,
    });
  }

  sendEmailConfirmationRequest(token: string) {
    return this.http.put(
      this.config.apiEndpoint + this.EMAIL_CONFIRMATION + token,
      {}
    );
  }

  logout() {
    return this.http.put(this.config.apiEndpoint + this.LOGOUT, {});
  }
}
