import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient} from "@angular/common/http";
import {Restaurant} from "../model/restaurant.model";

@Injectable({
  providedIn: 'root'
})
export class RestaurantsHttpService {
  GET_RESTAURANTS = 'restaurant/general-recommendation';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getRestaurants() {
    return this.http.get<Restaurant[]>(this.config.apiEndpoint + this.GET_RESTAURANTS);
  }
}
