import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {map, Observable, take} from 'rxjs';
import {Restaurant} from "../model/restaurant.model";
import {Store} from "@ngrx/store";
import {Actions, ofType} from "@ngrx/effects";
import * as RestaurantsActions from "../store/restaurants.actions";

@Injectable({
  providedIn: 'root'
})
export class SearchRestaurantsResolver implements Resolve<Restaurant[]> {
  constructor(
    private store: Store,
    private actions$: Actions<RestaurantsActions.RestaurantsActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<Restaurant[]> | Promise<Restaurant[]> | Restaurant[] {
    const type = route.params['type'];
    this.store.dispatch(
      RestaurantsActions.getRestaurantsByType({restaurantType: type})
    );
    return this.actions$.pipe(
      ofType(RestaurantsActions.setRestaurants.type),
      take(1),
      map((action) => action.restaurants)
    );
  }
}
