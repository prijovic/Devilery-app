import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { AdminReportsHttpService } from '../services/admin-reports-http.service';
import * as AdminReportsActions from './admin-reports.actions';
import { map, switchMap } from 'rxjs';

@Injectable()
export class AdminReportsEffects {
  getUnresolvedReports = createEffect(() =>
    this.actions$.pipe(
      ofType(AdminReportsActions.getUnresolvedReports.type),
      switchMap(() =>
        this.httpService
          .getUnresolvedReports()
          .pipe(
            map((reports) =>
              AdminReportsActions.setUnresolvedReports({ reports })
            )
          )
      )
    )
  );

  resolveReport = createEffect(() =>
    this.actions$.pipe(
      ofType(AdminReportsActions.resolveReport.type),
      switchMap((action) =>
        this.httpService
          .resolveReport(action.id, action.status)
          .pipe(
            map(() => AdminReportsActions.reportResolved({ id: action.id }))
          )
      )
    )
  );

  constructor(
    private store: Store,
    private actions$: Actions<AdminReportsActions.AdminReportsActionsUnion>,
    private httpService: AdminReportsHttpService
  ) {}
}
