import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {AddressService} from "../../../../../shared/services/address.service";
import {Store} from "@ngrx/store";
import {Subscription} from "rxjs";
import {selectOrderChargeEstimation} from "../../../../store/ordering.selectors";
import {Charge} from "../../../../../shared/model/charge.model";

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.scss']
})
export class OrderSummaryComponent implements OnInit, OnDestroy {
  @Output() estimationChanged = new EventEmitter<number>();
  storeSubscription!: Subscription;
  charge: Charge | null = null;

  constructor(private addressService: AddressService, private store: Store) {}

  ngOnInit() {
    this.storeSubscription = this.store.select(selectOrderChargeEstimation).subscribe(
      orderChargeEstimation => {
        if (orderChargeEstimation) {
          this.estimationChanged.emit(orderChargeEstimation.productsCost);
        }
        this.charge = orderChargeEstimation
      }
    );
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }

}
