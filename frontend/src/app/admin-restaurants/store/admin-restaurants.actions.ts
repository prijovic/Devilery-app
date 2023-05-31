import {createAction, props, union} from "@ngrx/store";

export const registerRestaurant = createAction(
  '[Admin Restaurants] Register Restaurant',
  props<{ description: string, opensAt: string, closesAt: string, name: string, addressName: string, longitude: number, latitude: number, minPreparation: number, maxPreparation: number, minOrder: number, picture: string, ownerId?: string }>()
);

const all = union({
  registerRestaurant
});

export type AdminRestaurantsActionsUnion = typeof all;
