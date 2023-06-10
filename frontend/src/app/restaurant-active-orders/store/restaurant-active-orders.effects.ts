import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Store } from '@ngrx/store';
import { RestaurantActiveOrdersHttpService } from '../services/restaurant-active-orders-http.service';
import * as ActiveRestaurantOrdersActions from './restaurant-active-orders.actions';
import { map, switchMap } from 'rxjs';

@Injectable()
export class RestaurantActiveOrdersEffects {
  getRestaurantId = createEffect(() =>
    this.actions$.pipe(
      ofType(ActiveRestaurantOrdersActions.getRestaurantId.type),
      switchMap(() =>
        this.httpService
          .getRestaurantId()
          .pipe(
            map((id) => ActiveRestaurantOrdersActions.setRestaurantId({ id }))
          )
      )
    )
  );

  getOrderById = createEffect(() =>
    this.actions$.pipe(
      ofType(ActiveRestaurantOrdersActions.getRestaurantOrderById.type),
      switchMap((action) =>
        this.httpService
          .getOrderById(action.id)
          .pipe(
            map((order) => ActiveRestaurantOrdersActions.setOrder({ order }))
          )
      )
    )
  );

  getActiveOrders = createEffect(() =>
    this.actions$.pipe(
      ofType(ActiveRestaurantOrdersActions.getActiveRestaurantOrders.type),
      switchMap(() =>
        this.httpService.getActiveOrders().pipe(
          map((orders) =>
            ActiveRestaurantOrdersActions.setActiveRestaurantOrders({
              restaurantOrders: orders,
            })
          )
        )
      )
    )
  );

  acceptOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(ActiveRestaurantOrdersActions.acceptOrder.type),
      switchMap((action) =>
        this.httpService.acceptOrder(action.id).pipe(
          map(() =>
            ActiveRestaurantOrdersActions.orderStatusChanged({
              id: action.id,
              status: 'ACCEPTED',
            })
          )
        )
      )
    )
  );

  rejectOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(ActiveRestaurantOrdersActions.rejectOrder.type),
      switchMap((action) =>
        this.httpService.rejectOrder(action.id, action.reason).pipe(
          map(() =>
            ActiveRestaurantOrdersActions.orderStatusChanged({
              id: action.id,
              status: 'REJECTED',
            })
          )
        )
      )
    )
  );

  finishOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(ActiveRestaurantOrdersActions.finishOrder.type),
      switchMap((action) =>
        this.httpService.finishOrder(action.id).pipe(
          map(() =>
            ActiveRestaurantOrdersActions.orderStatusChanged({
              id: action.id,
              status: 'DONE',
            })
          )
        )
      )
    )
  );

  constructor(
    private store: Store,
    private actions$: Actions<ActiveRestaurantOrdersActions.RestaurantActiveOrdersActionsUnion>,
    private httpService: RestaurantActiveOrdersHttpService
  ) {}
}
