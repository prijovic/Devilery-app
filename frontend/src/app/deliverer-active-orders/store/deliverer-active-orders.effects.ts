import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as DelivererActiveOrdersActions from '../store/deliverer-active-orders.actions';
import { DelivererActiveOrdersHttpService } from '../services/deliverer-active-orders-http.service';
import { map, switchMap } from 'rxjs';

@Injectable()
export class DelivererActiveOrdersEffects {
  getOrderById = createEffect(() =>
    this.actions$.pipe(
      ofType(DelivererActiveOrdersActions.getDelivererOrderById.type),
      switchMap((action) =>
        this.httpService
          .getOrderById(action.id)
          .pipe(
            map((order) => DelivererActiveOrdersActions.setOrder({ order }))
          )
      )
    )
  );

  getActiveOrders = createEffect(() =>
    this.actions$.pipe(
      ofType(DelivererActiveOrdersActions.getActiveDelivererOrders.type),
      switchMap(() =>
        this.httpService
          .getActiveOrders()
          .pipe(
            map((orders) =>
              DelivererActiveOrdersActions.setActiveDelivererOrders({ orders })
            )
          )
      )
    )
  );

  deliveringOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(DelivererActiveOrdersActions.deliveringOrder.type),
      switchMap((action) =>
        this.httpService.deliveringOrder(action.id).pipe(
          map(() =>
            DelivererActiveOrdersActions.orderStatusChanged({
              id: action.id,
              status: 'BEING_DELIVERED',
            })
          )
        )
      )
    )
  );

  failOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(DelivererActiveOrdersActions.failOrder.type),
      switchMap((action) =>
        this.httpService.failOrder(action.id, action.reason).pipe(
          map(() =>
            DelivererActiveOrdersActions.orderStatusChanged({
              id: action.id,
              status: 'UNSUCCESSFUL_DELIVERY',
            })
          )
        )
      )
    )
  );

  successOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(DelivererActiveOrdersActions.successOrder.type),
      switchMap((action) =>
        this.httpService.successOrder(action.id).pipe(
          map(() =>
            DelivererActiveOrdersActions.orderStatusChanged({
              id: action.id,
              status: 'DELIVERED',
            })
          )
        )
      )
    )
  );

  constructor(
    private store: Store,
    private actions$: Actions<DelivererActiveOrdersActions.DelivererActiveOrdersActionsUnion>,
    private httpService: DelivererActiveOrdersHttpService
  ) {}
}
