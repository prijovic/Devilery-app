import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RestaurantOrder } from '../model/restaurant-order.model';

@Injectable({
  providedIn: 'root',
})
export class RestaurantActiveOrdersHttpService {
  GET_RESTAURANT_ID = 'order/restaurant-id';
  GET_ORDER_BY_ID = 'order/restaurant';
  GET_ACTIVE_ORDERS = 'order/restaurant-active';
  ACCEPT_ORDER = (id: string) => `order/${id}/accept`;
  REJECT_ORDER = (id: string) => `order/${id}/reject`;
  FINISH_ORDER = (id: string) => `order/${id}/done`;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getRestaurantId() {
    return this.http.get<string>(
      this.config.apiEndpoint + this.GET_RESTAURANT_ID
    );
  }

  getOrderById(id: string) {
    return this.http.get<RestaurantOrder>(
      this.config.apiEndpoint + this.GET_ORDER_BY_ID,
      {
        params: new HttpParams().append('id', id),
      }
    );
  }

  getActiveOrders() {
    return this.http.get<RestaurantOrder[]>(
      this.config.apiEndpoint + this.GET_ACTIVE_ORDERS
    );
  }

  acceptOrder(id: string) {
    return this.http.put(this.config.apiEndpoint + this.ACCEPT_ORDER(id), {});
  }

  rejectOrder(id: string, reason: string) {
    return this.http.put(
      this.config.apiEndpoint + this.REJECT_ORDER(id),
      {},
      {
        params: new HttpParams().append('reason', reason),
      }
    );
  }

  finishOrder(id: string) {
    return this.http.put(this.config.apiEndpoint + this.FINISH_ORDER(id), {});
  }
}
