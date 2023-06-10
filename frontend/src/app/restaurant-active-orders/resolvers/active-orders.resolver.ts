import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import { RestaurantOrder } from '../model/restaurant-order.model';
import * as ActiveRestaurantOrdersActions from '../store/restaurant-active-orders.actions';
import { selectOrders } from '../store/restaurant-active-orders.selectors';

@Injectable({
  providedIn: 'root',
})
export class ActiveOrdersResolver implements Resolve<RestaurantOrder[]> {
  constructor(
    private store: Store,
    private actions$: Actions<ActiveRestaurantOrdersActions.RestaurantActiveOrdersActionsUnion>
  ) {}

  resolve():
    | Observable<RestaurantOrder[]>
    | Promise<RestaurantOrder[]>
    | RestaurantOrder[] {
    return this.store.select(selectOrders).pipe(
      take(1),
      switchMap((orders) => {
        if (orders.length === 0) {
          this.store.dispatch(
            ActiveRestaurantOrdersActions.getActiveRestaurantOrders()
          );
          return this.actions$.pipe(
            ofType(
              ActiveRestaurantOrdersActions.setActiveRestaurantOrders.type
            ),
            take(1),
            map((action) => action.restaurantOrders)
          );
        } else {
          return of(orders);
        }
      })
    );
  }
}
