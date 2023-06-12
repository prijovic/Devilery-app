import { Deliverer } from './deliverer.model';
import { User } from './user.model';
import { Charge } from './charge.model';
import { Address } from './address.model';
import { Restaurant } from './restaurant.model';
import { RestaurantItem } from './restaurant-item.model';

export class Order {
  constructor(
    public id: string,
    public createdOn: Date,
    public deliverer: Deliverer,
    public customer: User,
    public status: string,
    public deliveryDistance: number,
    public charge: Charge,
    public address: Address,
    public restaurant: Restaurant,
    public items: RestaurantItem[],
    public rejectionReason: string,
    public unsuccessfulDeliveryReason: string,
    public isReportSubmitted: boolean
  ) {}
}
