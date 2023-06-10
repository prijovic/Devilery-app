import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as CoreActions from '../store/core.actions';
import { catchError, map, of, switchMap, tap } from 'rxjs';
import { PictureService } from '../../services/picture.service';
import { Router } from '@angular/router';
import { NotifierService } from '../notifier.service';

@Injectable()
export class CoreEffects {
  notifySuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CoreActions.notifySuccess.type),
        tap((action) => {
          this.notifierService.notifySuccess(action.message, action.title);
        })
      );
    },
    { dispatch: false }
  );

  notifyInfo = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CoreActions.notifyInfo.type),
        tap((action) => {
          this.notifierService.notifyInfo(action.message, action.title);
        })
      );
    },
    { dispatch: false }
  );

  fileCleanUp = createEffect(() => {
    return this.actions$.pipe(
      ofType(CoreActions.cleanUpFile.type),
      switchMap((action) => {
        return this.pictureService.deletePicture(action.fileName).pipe(
          map(() => CoreActions.cleanUpFileSuccess()),
          catchError(() => of(CoreActions.cleanUpFileFail()))
        );
      })
    );
  });

  constructor(
    private notifierService: NotifierService,
    private pictureService: PictureService,
    private router: Router,
    private actions$: Actions<CoreActions.CoreActionsUnion>
  ) {}
}
