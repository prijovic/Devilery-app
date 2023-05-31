import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as AdminDeliverersActions from "../store/admin-deliverers.actions";
import {AdminDeliverersHttpService} from "../services/admin-deliverers-http.service";
import {catchError, map, of, switchMap} from "rxjs";
import * as CoreActions from "../../core/store/core.actions";

@Injectable()
export class AdminDeliverersEffects {

  deliverer_registration = createEffect(() => {
    return this.actions$.pipe(
      ofType(AdminDeliverersActions.registerDeliverer.type),
      switchMap((action) => {
        return this.httpService
          .sendDelivererRegistrationRequest(action.email, action.password, action.name, action.surname, action.phoneNumber, action.profilePicture, action.delivererType)
          .pipe(
            map(() => CoreActions.notifySuccess({message: 'Your have successfully registered a new deliverer.', title: 'Deliverer Registration'})),
            catchError(() => of(CoreActions.cleanUpFile({fileName: action.profilePicture})))
          );
      })
    );
  });

  constructor(
    private actions$: Actions<AdminDeliverersActions.AdminDeliverersActionsUnion>,
    private httpService: AdminDeliverersHttpService,
  ) {}
}
