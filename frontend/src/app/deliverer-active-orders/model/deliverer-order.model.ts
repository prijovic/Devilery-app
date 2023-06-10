import { Charge } from '../../shared/model/charge.model';
import { User } from '../../shared/model/user.model';
import { Address } from '../../shared/model/address.model';
import { Restaurant } from '../../shared/model/restaurant.model';

export class DelivererOrder {
  constructor(
    public id: string,
    public status: string,
    public charge: Charge,
    public customer: User,
    public address: Address,
    public restaurant: Restaurant
  ) {}
}
