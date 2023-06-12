import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient } from '@angular/common/http';
import { Order } from '../../shared/model/order.model';

@Injectable({
  providedIn: 'root',
})
export class OrderHistoryHttpService {
  private GET_ORDER_HISTORY = 'order/history';
  private SUBMIT_REPORT = 'report/';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getOrderHistory() {
    return this.http.get<Order[]>(
      this.config.apiEndpoint + this.GET_ORDER_HISTORY
    );
  }

  submitReport(orderId: string, comment: string) {
    return this.http.post(this.config.apiEndpoint + this.SUBMIT_REPORT, {
      orderId,
      comment,
    });
  }
}
