import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as RestaurantsActions from "./restaurants.actions";
import {map, switchMap} from "rxjs";
import {RestaurantsHttpService} from "../services/restaurants-http.service";

@Injectable()
export class RestaurantsEffects {

  get_restaurants = createEffect(() => {
    return this.actions$.pipe(
      ofType(RestaurantsActions.getRestaurants.type),
      switchMap(() => {
        return this.httpService.getRestaurants()
          .pipe(
            map((restaurants) => RestaurantsActions.setRestaurants({restaurants}))
          );
      })
    );
  });

  get_restaurants_by_type = createEffect(() => {
    return this.actions$.pipe(
      ofType(RestaurantsActions.getRestaurantsByType.type),
      switchMap((action) => {
        return this.httpService.getRestaurantsByType(action.restaurantType)
          .pipe(
            map((restaurants) => RestaurantsActions.setRestaurants({restaurants}))
          );
      })
    );
  });

  constructor(
    private actions$: Actions<RestaurantsActions.RestaurantsActionsUnion>,
    private httpService: RestaurantsHttpService,
  ) {}
}
