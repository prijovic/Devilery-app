import {createAction, props, union} from "@ngrx/store";

export const registerDeliverer = createAction(
  '[Admin Deliverers] Register Deliverer',
  props<{ email: string, password: string, name: string, surname: string, phoneNumber: string, profilePicture: string, delivererType: string }>()
);

const all = union({
  registerDeliverer,
});

export type AdminDeliverersActionsUnion = typeof all;
