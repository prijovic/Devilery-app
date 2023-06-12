import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, of, switchMap, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import { Report } from '../model/report.model';
import * as AdminReportsActions from '../store/admin-reports.actions';
import { selectReports } from '../store/admin-reports.selectors';

@Injectable({
  providedIn: 'root',
})
export class UnresolvedReportsResolver implements Resolve<Report[]> {
  constructor(
    private store: Store,
    private actions$: Actions<AdminReportsActions.AdminReportsActionsUnion>
  ) {}

  resolve(): Observable<Report[]> | Promise<Report[]> | Report[] {
    return this.store.select(selectReports).pipe(
      take(1),
      switchMap((reports) => {
        if (reports.length === 0) {
          this.store.dispatch(AdminReportsActions.getUnresolvedReports());
          return this.actions$.pipe(
            ofType(AdminReportsActions.setUnresolvedReports.type),
            take(1),
            map((action) => action.reports)
          );
        } else {
          return of(reports);
        }
      })
    );
  }
}
