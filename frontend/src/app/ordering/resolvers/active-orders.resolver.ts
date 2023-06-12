import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import { Order } from '../../shared/model/order.model';
import * as OrderingActions from '../store/ordering.actions';
import { selectActiveOrders } from '../store/ordering.selectors';

@Injectable({
  providedIn: 'root',
})
export class ActiveOrdersResolver implements Resolve<Order[]> {
  constructor(
    private store: Store,
    private actions$: Actions<OrderingActions.OrderingActionsUnion>
  ) {}

  resolve(): Observable<Order[]> | Promise<Order[]> | Order[] {
    return this.store.select(selectActiveOrders).pipe(
      take(1),
      switchMap((orders) => {
        if (orders.length === 0) {
          this.store.dispatch(OrderingActions.getActiveOrders());
          return this.actions$.pipe(
            ofType(OrderingActions.setActiveOrders.type),
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
