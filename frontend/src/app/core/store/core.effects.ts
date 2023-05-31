import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as CoreActions from "../store/core.actions";
import {catchError, map, of, switchMap} from "rxjs";
import {PictureService} from "../../services/picture.service";
import {Router} from "@angular/router";

@Injectable()
export class CoreEffects {
  file_clean_up = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CoreActions.cleanUpFile.type),
        switchMap((action) => {
          return this.pictureService.deletePicture(action.fileName)
            .pipe(
              map(() => CoreActions.cleanUpFileSuccess()),
              catchError(() => of(CoreActions.cleanUpFileFail()))
            );
        })
      );
    }
  );

  constructor(
    private pictureService: PictureService,
    private router: Router,
    private actions$: Actions<CoreActions.CoreActionsUnion>,
  ) {}
}
