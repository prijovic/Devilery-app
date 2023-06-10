import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as RestaurantsActions from './restaurants.actions';
import { map, switchMap } from 'rxjs';
import { RestaurantsHttpService } from '../services/restaurants-http.service';

@Injectable()
export class RestaurantsEffects {
  getRestaurants = createEffect(() => {
    return this.actions$.pipe(
      ofType(RestaurantsActions.getRestaurants.type),
      switchMap(() => {
        return this.httpService
          .getRestaurants()
          .pipe(
            map((restaurants) =>
              RestaurantsActions.setRestaurants({ restaurants })
            )
          );
      })
    );
  });

  getRestaurantsByType = createEffect(() => {
    return this.actions$.pipe(
      ofType(RestaurantsActions.getRestaurantsByType.type),
      switchMap((action) => {
        return this.httpService
          .getRestaurantsByType(action.restaurantType)
          .pipe(
            map((restaurants) =>
              RestaurantsActions.setRestaurants({ restaurants })
            )
          );
      })
    );
  });

  getRestaurantsByText = createEffect(() => {
    return this.actions$.pipe(
      ofType(RestaurantsActions.getRestaurantsByText.type),
      switchMap((action) => {
        return this.httpService
          .getRestaurantsByText(action.text)
          .pipe(
            map((restaurants) =>
              RestaurantsActions.setRestaurants({ restaurants })
            )
          );
      })
    );
  });

  getRestaurantItems = createEffect(() => {
    return this.actions$.pipe(
      ofType(RestaurantsActions.getRestaurantMenuItems.type),
      switchMap((action) => {
        return this.httpService
          .getRestaurantItems(action.restaurantId)
          .pipe(
            map((restaurantItems) =>
              RestaurantsActions.setRestaurantMenuItems({ restaurantItems })
            )
          );
      })
    );
  });

  constructor(
    private actions$: Actions<RestaurantsActions.RestaurantsActionsUnion>,
    private httpService: RestaurantsHttpService
  ) {}
}
