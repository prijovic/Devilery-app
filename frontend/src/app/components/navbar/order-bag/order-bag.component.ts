import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Subscription} from "rxjs";
import {Store} from "@ngrx/store";
import {selectItems} from "../../../ordering/store/ordering.selectors";

@Component({
  selector: 'app-order-bag',
  templateUrl: './order-bag.component.html',
  styleUrls: ['./order-bag.component.scss']
})
export class OrderBagComponent implements OnInit, OnDestroy {
  itemsCount = new BehaviorSubject(0);
  storeSubscription!: Subscription;
  constructor(private store: Store) {}

  ngOnInit() {
    this.storeSubscription = this.store.select(selectItems).subscribe(items => this.itemsCount.next(items.length));
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }
}
