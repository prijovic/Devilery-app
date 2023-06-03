import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as OrderingActions from "./ordering.actions";
import {OrderingHttpService} from "../services/ordering-http.service";
import {filter, tap, withLatestFrom} from "rxjs";
import {Store} from "@ngrx/store";
import {selectOrderingFeature} from "./ordering.selectors";

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

  constructor(
    private store: Store,
    private actions$: Actions<OrderingActions.OrderingActionsUnion>,
    private httpService: OrderingHttpService,
  ) {}
}
