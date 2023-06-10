import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DelivererOrder } from '../model/deliverer-order.model';

@Injectable({
  providedIn: 'root',
})
export class DelivererActiveOrdersHttpService {
  GET_ORDER_BY_ID = 'order/deliverer';
  GET_ACTIVE_ORDERS = 'order/deliverer-active';
  DELIVERING_ORDER = (id: string) => `order/${id}/delivering`;
  SUCCESS_ORDER = (id: string) => `order/${id}/success`;
  FAIL_ORDER = (id: string) => `order/${id}/fail`;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getOrderById(id: string) {
    return this.http.get<DelivererOrder>(
      this.config.apiEndpoint + this.GET_ORDER_BY_ID,
      {
        params: new HttpParams().append('id', id),
      }
    );
  }

  getActiveOrders() {
    return this.http.get<DelivererOrder[]>(
      this.config.apiEndpoint + this.GET_ACTIVE_ORDERS
    );
  }

  deliveringOrder(id: string) {
    return this.http.put(
      this.config.apiEndpoint + this.DELIVERING_ORDER(id),
      {}
    );
  }

  failOrder(id: string, reason: string) {
    return this.http.put(
      this.config.apiEndpoint + this.FAIL_ORDER(id),
      {},
      {
        params: new HttpParams().append('reason', reason),
      }
    );
  }

  successOrder(id: string) {
    return this.http.put(this.config.apiEndpoint + this.SUCCESS_ORDER(id), {});
  }
}
