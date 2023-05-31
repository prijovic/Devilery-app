import {createAction, props, union} from "@ngrx/store";

export const registerDeliverer = createAction(
  '[Admin Deliverers] Register Deliverer',
  props<{ email: string, password: string, name: string, surname: string, phoneNumber: string, profilePicture: string, delivererType: string }>()
);

export const registerDelivererSuccess = createAction('[Admin Deliverers] Register Deliverer Success');

const all = union({
  registerDeliverer,
  registerDelivererSuccess,
});

export type AdminDeliverersActionsUnion = typeof all;
