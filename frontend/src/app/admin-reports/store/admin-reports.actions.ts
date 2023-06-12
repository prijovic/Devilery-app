import { createAction, props, union } from '@ngrx/store';
import { Report } from '../model/report.model';

export const getUnresolvedReports = createAction(
  '[Admin Reports] Get Unresolved Reports'
);

export const setUnresolvedReports = createAction(
  '[Admin Reports] Set Unresolved Reports',
  props<{ reports: Report[] }>()
);

export const resolveReport = createAction(
  '[Admin Reports] Resolve Report',
  props<{ id: string; status: string }>()
);

export const reportResolved = createAction(
  '[Admin Reports] Report Resolved',
  props<{ id: string }>()
);

const all = union({
  getUnresolvedReports,
  setUnresolvedReports,
  resolveReport,
  reportResolved,
});

export type AdminReportsActionsUnion = typeof all;
