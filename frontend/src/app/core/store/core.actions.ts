import {createAction, props, union} from "@ngrx/store";

export const cleanUpFile = createAction('[Core] Clean Up File', props<{fileName: string}>());

export const cleanUpFileSuccess = createAction('[Core] Clean Up File Success');

export const cleanUpFileFail = createAction('[Core] Clean Up File Fail');

const all = union({
  cleanUpFile,
  cleanUpFileSuccess,
  cleanUpFileFail
});

export type CoreActionsUnion = typeof all;
