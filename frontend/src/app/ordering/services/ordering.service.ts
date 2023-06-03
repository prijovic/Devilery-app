import { Injectable } from '@angular/core';
import {Store} from "@ngrx/store";
import {selectItems} from "../store/ordering.selectors";
import {RestaurantItem} from "../../shared/model/restaurant-item.model";

@Injectable({
  providedIn: 'root'
})
export class OrderingService {

  constructor(private store: Store) {}

  getItemCountById(itemId: string): number {
    let itemCount = 0;
    this.store.select(selectItems).subscribe((items: RestaurantItem[]) => itemCount = items.filter(item => item.id === itemId).length);
    return itemCount;
  }
}
