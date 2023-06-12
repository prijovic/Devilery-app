import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, switchMap, tap } from 'rxjs';
import { Store } from '@ngrx/store';
import * as OrderHistoryActions from './order-history.actions';
import { OrderHistoryHttpService } from '../services/order-history-http.service';
import { notifySuccess } from '../../core/store/core.actions';

@Injectable()
export class OrderHistoryEffects {
  getOrderHistory = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderHistoryActions.getOrderHistory.type),
      switchMap(() =>
        this.httpService
          .getOrderHistory()
          .pipe(
            map((orders) => OrderHistoryActions.setOrderHistory({ orders }))
          )
      )
    )
  );

  submitReport = createEffect(() =>
    this.actions$.pipe(
      ofType(OrderHistoryActions.submitReport.type),
      switchMap((action) =>
        this.httpService.submitReport(action.orderId, action.comment).pipe(
          tap(() => {
            this.store.dispatch(
              notifySuccess({
                message:
                  'You have successfully submited report. Our admin will review it as soon as possible.',
                title: 'Report Submission',
              })
            );
          }),
          map(() =>
            OrderHistoryActions.reportSubmitted({ orderId: action.orderId })
          )
        )
      )
    )
  );

  constructor(
    private store: Store,
    private actions$: Actions<OrderHistoryActions.OrderHistoryActionsUnion>,
    private httpService: OrderHistoryHttpService
  ) {}
}
