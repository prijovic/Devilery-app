import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {AddressService} from "../../../../../shared/services/address.service";
import {Store} from "@ngrx/store";
import {BehaviorSubject, map, Subscription} from "rxjs";
import {selectItems} from "../../../../store/ordering.selectors";

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.scss']
})
export class OrderSummaryComponent implements OnInit, OnDestroy {
  storeSubscription!: Subscription;
  @Input() distance = 0;
  itemCost = new BehaviorSubject(0);
  @Input() discount = 0;

  constructor(private addressService: AddressService, private store: Store) {}

  get deliveryCost(): number {
    return this.distance * 1.1;
  }

  get price(): number {
    return (this.itemCost.getValue() + this.deliveryCost);
  }

  get totalPrice(): number {
    return this.price - this.discountValue;
  }

  get discountValue(): number {
    return this.price * this.discount;
  }

  ngOnInit() {
    this.storeSubscription = this.store.select(selectItems).pipe(
      map(items => items.reduce((previousValue, currentValue) => {return {...currentValue, price:currentValue.price + previousValue.price}}))
    ).subscribe(item => this.itemCost.next(item.price));
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }
}
