import { Report } from '../model/report.model';
import { Action, createReducer, on } from '@ngrx/store';
import * as AdminReportsActions from './admin-reports.actions';

export interface State {
  reports: Report[];
}

const initialState: State = {
  reports: [],
};

const restaurantActiveOrdersReducer = createReducer(
  initialState,
  on(AdminReportsActions.setUnresolvedReports, (state, { reports }) => ({
    ...state,
    reports,
  })),
  on(AdminReportsActions.reportResolved, (state, { id }) => ({
    ...state,
    reports: state.reports.filter((report) => report.id !== id),
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return restaurantActiveOrdersReducer(state, action);
}
