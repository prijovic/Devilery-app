import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminRestaurantsHttpService {
  RESTAURANT_REGISTRATION = 'restaurant/'

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  sendRestaurantRegistrationRequest(name: string, description: string, opensAt: string, closesAt: string, addressName: string, longitude: number, latitude: number, minPreparation: number, maxPreparation: number, minOrder: number, picture: string, ownerId?: string) {
    return this.http.post(this.config.apiEndpoint + this.RESTAURANT_REGISTRATION, {
      name,
      description,
      opensAt,
      closesAt,
      addressName,
      longitude,
      latitude,
      minPreparation,
      maxPreparation,
      minOrder,
      picture,
      ownerId
    });
  }
}
