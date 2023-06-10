import { createAction, props, union } from '@ngrx/store';

export const notifySuccess = createAction(
  '[Core] Notify Success',
  props<{ message: string; title: string }>()
);

export const notifyInfo = createAction(
  '[Core] Notify Info',
  props<{ message: string; title: string }>()
);

export const cleanUpFile = createAction(
  '[Core] Clean Up File',
  props<{ fileName: string }>()
);

export const cleanUpFileSuccess = createAction('[Core] Clean Up File Success');

export const cleanUpFileFail = createAction('[Core] Clean Up File Fail');

const all = union({
  notifySuccess,
  notifyInfo,
  cleanUpFile,
  cleanUpFileSuccess,
  cleanUpFileFail,
});

export type CoreActionsUnion = typeof all;
