import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './admin-reports.reducer';

export const selectAdminReportsFeature =
  createFeatureSelector<State>('admin-reports');

export const selectReports = createSelector(
  selectAdminReportsFeature,
  (state) => state.reports
);
