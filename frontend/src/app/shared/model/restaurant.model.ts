import { Address } from './address.model';

export class Restaurant {
  constructor(
    public id: string,
    public name: string,
    public description: string,
    public opensAt: string,
    public closesAt: string,
    public picture: string,
    public minOrder: number,
    public minPrep: number,
    public maxPrep: number,
    public rating: number,
    public isClosed: boolean,
    public isNew: boolean,
    public address: Address,
    public specializedTypes: string[]
  ) {}
}
