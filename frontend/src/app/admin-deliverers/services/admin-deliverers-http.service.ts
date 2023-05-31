import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminDeliverersHttpService {
  DELIVERER_REGISTRATION = 'deliverer/'

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  sendDelivererRegistrationRequest(email: string, password: string, name: string, surname: string, phoneNumber: string, profilePicture: string, type: string) {
    return this.http.post(this.config.apiEndpoint + this.DELIVERER_REGISTRATION, {
      email,
      password,
      name,
      surname,
      phoneNumber,
      profilePicture,
      type
    });
  }
}
