import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot
} from '@angular/router';
import {map, Observable, take} from 'rxjs';
import {Store} from "@ngrx/store";
import {Actions, ofType} from "@ngrx/effects";
import * as RestaurantsActions from "../store/restaurants.actions";
import {Restaurant} from "../model/restaurant.model";

@Injectable({
  providedIn: 'root'
})
export class RestaurantsResolver implements Resolve<Restaurant[]> {
  constructor(
    private store: Store,
    private actions$: Actions<RestaurantsActions.RestaurantsActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<Restaurant[]> | Promise<Restaurant[]> | Restaurant[] {
    this.store.dispatch(
      RestaurantsActions.getRestaurants()
    );
    return this.actions$.pipe(
      ofType(RestaurantsActions.setRestaurants.type),
      take(1),
      map((action) => action.restaurants)
    );
  }
}
