import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as OrderingActions from "./ordering.actions";
import {OrderingHttpService} from "../services/ordering-http.service";
import {filter, map, switchMap, tap, withLatestFrom} from "rxjs";
import {Store} from "@ngrx/store";
import {selectOrderingFeature} from "./ordering.selectors";
import {notifySuccess} from "../../core/store/core.actions";
import {Router} from "@angular/router";

@Injectable()
export class OrderingEffects {
  addItemToOrder = createEffect(() =>
      this.actions$.pipe(
        ofType(OrderingActions.addItemToOrder.type),
        withLatestFrom(this.store.select(selectOrderingFeature)),
        filter(([action, state]) => state.restaurantId !== null && action.restaurantId !== state.restaurantId),
        tap(([action, ]) => {
          this.store.dispatch(OrderingActions.askForNewOrderAttempt({restaurantId: action.restaurantId, item: action.item}));
        })
      ),
    { dispatch: false }
  );

  getOrderChargeEstimation = createEffect(() => this.actions$.pipe(
    ofType(OrderingActions.getOrderChargeEstimation.type),
    switchMap((action) => {
      return this.httpService.getOrderChargeEstimation(action.itemIds, action.deliveryDistance).pipe(
        map(orderChargeEstimation => OrderingActions.setOrderChargeEstimation({orderChargeEstimation})));
    })
  ));

  createOrder = createEffect(() => this.actions$.pipe(
    ofType(OrderingActions.createOrder.type),
    withLatestFrom(this.store.select(selectOrderingFeature)),
    switchMap(([action, state]) => {
      return this.httpService.createNewOrder(state.items.map(item => item.id), action.deliveryDistance, action.addressId).pipe(
        map(() => OrderingActions.createOrderSuccess()));
    })
  ));

  createOrderSuccess = createEffect(() =>
      this.actions$.pipe(
        ofType(OrderingActions.addItemToOrder.type),
        tap(() => this.router.navigate(['/'])),
        map(() => notifySuccess({message: 'Wait for restaurant stuff to accept your order', title:'New Order'}))
      )
  );

  constructor(
    private store: Store,
    private actions$: Actions<OrderingActions.OrderingActionsUnion>,
    private httpService: OrderingHttpService,
    private router: Router
  ) {}
}
