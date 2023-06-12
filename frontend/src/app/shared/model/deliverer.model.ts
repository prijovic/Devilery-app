import { User } from './user.model';

export class Deliverer extends User {
  constructor(
    id: string,
    name: string,
    surname: string,
    email: string,
    phoneNumber: string,
    profilePicture: string,
    public type: string,
    public rating: number
  ) {
    super(id, name, surname, email, phoneNumber, profilePicture);
  }
}
