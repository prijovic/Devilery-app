import { RestaurantItem } from '../../shared/model/restaurant-item.model';
import { Charge } from '../../shared/model/charge.model';

export class RestaurantOrder {
  constructor(
    public id: string,
    public status: string,
    public items: RestaurantItem[],
    public charge: Charge
  ) {}
}
