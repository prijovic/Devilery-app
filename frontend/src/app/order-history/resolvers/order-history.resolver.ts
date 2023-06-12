import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Order } from '../../shared/model/order.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import { selectOrders } from '../store/order-histoy.selectors';
import * as OrderHistoryActions from '../store/order-history.actions';

@Injectable({
  providedIn: 'root',
})
export class OrderHistoryResolver implements Resolve<Order[]> {
  constructor(
    private store: Store,
    private actions$: Actions<OrderHistoryActions.OrderHistoryActionsUnion>
  ) {}

  resolve(): Observable<Order[]> | Promise<Order[]> | Order[] {
    return this.store.select(selectOrders).pipe(
      take(1),
      switchMap((orders) => {
        if (orders.length === 0) {
          this.store.dispatch(OrderHistoryActions.getOrderHistory());
          return this.actions$.pipe(
            ofType(OrderHistoryActions.setOrderHistory.type),
            take(1),
            map((action) => action.orders)
          );
        } else {
          return of(orders);
        }
      })
    );
  }
}
