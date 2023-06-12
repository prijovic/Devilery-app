import { User } from '../../shared/model/user.model';
import { Deliverer } from '../../shared/model/deliverer.model';

export class Report {
  constructor(
    public id: string,
    public createdOn: Date,
    public status: string,
    public customer: User,
    public deliverer: Deliverer,
    public comment: string
  ) {}
}
