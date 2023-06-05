import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Charge} from "../../shared/model/charge.model";

@Injectable({
  providedIn: 'root'
})
export class OrderingHttpService {
  GET_ORDER_PRICE_ESTIMATION = 'order/price';
  CREATE_ORDER = 'order/';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) { }

  getOrderChargeEstimation(itemIds: string[], deliveryDistance: number) {
    return this.http.get<Charge>(this.config.apiEndpoint + this.GET_ORDER_PRICE_ESTIMATION, {
      params: new HttpParams().append('itemIds', itemIds.join(',')).append('deliveryDistance', deliveryDistance)
    });
  }

  createNewOrder(itemIds: string[], deliveryDistance: number, addressId: string) {
    return this.http.post(this.config.apiEndpoint + this.CREATE_ORDER, {
      itemIds,
      deliveryDistance,
      addressId
    });
  }
}
