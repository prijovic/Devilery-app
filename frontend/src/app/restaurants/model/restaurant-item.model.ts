export class RestaurantItem {
  constructor(
    public name: string,
    public picture: string,
    public description: string,
    public price: number,
    public available: boolean,
    public type: string
  ) {}
}
