export class Charge {
  constructor(
    public productsCost: number,
    public servicesFee: number,
    public deliveryFee: number,
    public userDiscount: number,
    public discountValue: number,
    public total: number,
  ) {
  }
}
