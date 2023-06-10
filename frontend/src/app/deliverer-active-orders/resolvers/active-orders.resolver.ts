import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as DelivererActiveOrdersActions from '../store/deliverer-active-orders.actions';
import { DelivererOrder } from '../model/deliverer-order.model';
import { selectOrders } from '../store/deliverer-active-orders.selectors';

@Injectable({
  providedIn: 'root',
})
export class ActiveOrdersResolver implements Resolve<DelivererOrder[]> {
  constructor(
    private store: Store,
    private actions$: Actions<DelivererActiveOrdersActions.DelivererActiveOrdersActionsUnion>
  ) {}

  resolve():
    | Observable<DelivererOrder[]>
    | Promise<DelivererOrder[]>
    | DelivererOrder[] {
    return this.store.select(selectOrders).pipe(
      take(1),
      switchMap((orders) => {
        if (orders.length === 0) {
          this.store.dispatch(
            DelivererActiveOrdersActions.getActiveDelivererOrders()
          );
          return this.actions$.pipe(
            ofType(DelivererActiveOrdersActions.setActiveDelivererOrders.type),
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
