import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot
} from '@angular/router';
import {map, Observable, take} from 'rxjs';
import {Store} from "@ngrx/store";
import {Actions, ofType} from "@ngrx/effects";
import * as RestaurantsActions from "../store/restaurants.actions";
import {RestaurantItem} from "../model/restaurant-item.model";

@Injectable({
  providedIn: 'root'
})
export class RestaurantItemsResolver implements Resolve<RestaurantItem[]> {
  constructor(
    private store: Store,
    private actions$: Actions<RestaurantsActions.RestaurantsActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<RestaurantItem[]> | Promise<RestaurantItem[]> | RestaurantItem[] {
    const id = route.params['id'];
    this.store.dispatch(
      RestaurantsActions.getRestaurantMenuItems({restaurantId: id})
    );
    return this.actions$.pipe(
      ofType(RestaurantsActions.setRestaurantMenuItems.type),
      take(1),
      map((action) => action.restaurantItems)
    );
  }
}
