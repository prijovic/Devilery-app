import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as AdminRestaurantsActions from "./admin-restaurants.actions";
import {AdminRestaurantsHttpService} from "../services/admin-restaurants-http.service";
import {catchError, map, of, switchMap} from "rxjs";
import * as CoreActions from "../../core/store/core.actions";

@Injectable()
export class AdminRestaurantsEffects {

  restaurant_registration = createEffect(() => {
    return this.actions$.pipe(
      ofType(AdminRestaurantsActions.registerRestaurant.type),
      switchMap((action) => {
        return this.httpService
          .sendRestaurantRegistrationRequest(action.name, action.description, action.opensAt, action.closesAt, action.addressName, action.longitude, action.latitude, action.minPreparation, action.maxPreparation, action.minOrder, action.picture, action.ownerId)
          .pipe(
            map(() => CoreActions.notifySuccess({message: 'Your have successfully registered a new restaurant.', title: 'Restaurant Registration'})),
            catchError(() => of(CoreActions.cleanUpFile({fileName: action.picture})))
          );
      })
    );
  });

  constructor(
    private actions$: Actions<AdminRestaurantsActions.AdminRestaurantsActionsUnion>,
    private httpService: AdminRestaurantsHttpService,
  ) {}
}
