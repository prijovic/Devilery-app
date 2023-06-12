import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as OrderingActions from './ordering.actions';
import { OrderingHttpService } from '../services/ordering-http.service';
import { filter, map, switchMap, tap, withLatestFrom } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectOrderingFeature } from './ordering.selectors';
import { notifySuccess } from '../../core/store/core.actions';
import { Router } from '@angular/router';

@Injectable()
export class OrderingEffects {
  addItemToOrder = createEffect(
    () =>
      this.actions$.pipe(
        ofType(OrderingActions.addItemToOrder.type),
        withLatestFrom(this.store.select(selectOrderingFeature)),
        filter(
          ([action, state]) =>
            state.restaurantId !== null &&
            action.restaurantId !== state.restaurantId
        ),
        tap(([action]) => {
          this.store.dispatch(
            OrderingActions.askForNewOrderAttempt({
              restaurantId: action.restaurantId,
              item: action.item,
            })
          );
        })
      ),
    { dispatch: false }
  );

  getOrderChargeEstimation = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderingActions.getOrderChargeEstimation.type),
      switchMap((action) => {
        return this.httpService
          .getOrderChargeEstimation(action.itemIds, action.deliveryDistance)
          .pipe(
            map((orderChargeEstimation) =>
              OrderingActions.setOrderChargeEstimation({
                orderChargeEstimation,
              })
            )
          );
      })
    )
  );

  createOrder = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderingActions.createOrder.type),
      withLatestFrom(this.store.select(selectOrderingFeature)),
      switchMap(([action, state]) => {
        return this.httpService
          .createNewOrder(
            state.items.map((item) => item.id),
            action.deliveryDistance,
            action.addressId
          )
          .pipe(map(() => OrderingActions.createOrderSuccess()));
      })
    )
  );

  createOrderSuccess = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderingActions.createOrderSuccess.type),
      tap(() => this.router.navigate(['/'])),
      map(() =>
        notifySuccess({
          message: 'Wait for restaurant stuff to accept your order',
          title: 'New Order',
        })
      )
    )
  );

  getActiveOrders = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderingActions.getActiveOrders.type),
      switchMap(() => {
        return this.httpService
          .getActiveOrders()
          .pipe(map((orders) => OrderingActions.setActiveOrders({ orders })));
      })
    )
  );

  submitReview = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderingActions.submitReview.type),
      switchMap((action) => {
        return this.httpService
          .submitReview(
            action.orderId,
            action.delivererRating,
            action.delivererComment,
            action.restaurantRating,
            action.restaurantComment
          )
          .pipe(
            map(() =>
              notifySuccess({
                message:
                  'You have successfully submitted review. Thank you for contributing our community!',
                title: 'Review Submission',
              })
            )
          );
      })
    )
  );

  constructor(
    private store: Store,
    private actions$: Actions<OrderingActions.OrderingActionsUnion>,
    private httpService: OrderingHttpService,
    private router: Router
  ) {}
}
