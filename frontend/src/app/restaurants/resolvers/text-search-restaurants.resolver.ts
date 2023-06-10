import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { Restaurant } from '../../shared/model/restaurant.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as RestaurantsActions from '../store/restaurants.actions';

@Injectable({
  providedIn: 'root',
})
export class TextSearchRestaurantsResolver implements Resolve<Restaurant[]> {
  constructor(
    private store: Store,
    private actions$: Actions<RestaurantsActions.RestaurantsActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<Restaurant[]> | Promise<Restaurant[]> | Restaurant[] {
    const text = route.params['text'];
    this.store.dispatch(RestaurantsActions.getRestaurantsByText({ text }));
    return this.actions$.pipe(
      ofType(RestaurantsActions.setRestaurants.type),
      take(1),
      map((action) => action.restaurants)
    );
  }
}
