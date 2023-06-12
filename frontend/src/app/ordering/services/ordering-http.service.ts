import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Charge } from '../../shared/model/charge.model';
import { Order } from '../../shared/model/order.model';

@Injectable({
  providedIn: 'root',
})
export class OrderingHttpService {
  GET_ACTIVE_ORDERS = 'order/active';
  GET_ORDER_PRICE_ESTIMATION = 'order/price';
  CREATE_ORDER = 'order/';
  SUBMIT_REVIEW = 'review/';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getActiveOrders() {
    return this.http.get<Order[]>(
      this.config.apiEndpoint + this.GET_ACTIVE_ORDERS
    );
  }

  getOrderChargeEstimation(itemIds: string[], deliveryDistance: number) {
    return this.http.get<Charge>(
      this.config.apiEndpoint + this.GET_ORDER_PRICE_ESTIMATION,
      {
        params: new HttpParams()
          .append('itemIds', itemIds.join(','))
          .append('deliveryDistance', deliveryDistance),
      }
    );
  }

  createNewOrder(
    itemIds: string[],
    deliveryDistance: number,
    addressId: string
  ) {
    return this.http.post(this.config.apiEndpoint + this.CREATE_ORDER, {
      itemIds,
      deliveryDistance,
      addressId,
    });
  }

  submitReview(
    orderId: string,
    delivererRating: number,
    delivererComment: string,
    restaurantRating: number,
    restaurantComment: string
  ) {
    return this.http.post(this.config.apiEndpoint + this.SUBMIT_REVIEW, {
      orderId,
      delivererRating,
      delivererComment,
      restaurantRating,
      restaurantComment,
    });
  }
}
