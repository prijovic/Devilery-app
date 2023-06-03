import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {BehaviorSubject} from "rxjs";
import {SelfService} from "../../../../../shared/services/self.service";
import {MapComponent} from "../../../../../shared/components/map/map.component";
import {MatSelectChange} from "@angular/material/select";
import {Address} from "../../../../../shared/model/address.model";

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.scss']
})
export class DeliveryDetailsComponent implements OnInit {
  @Output() addressSelected = new EventEmitter<Address>();
  addressControl = new FormControl(null, Validators.required);
  addresses: BehaviorSubject<any> = new BehaviorSubject(null);

  constructor(private selfService: SelfService, private mapComponent: MapComponent) {
  }

  ngOnInit() {
    this.selfService.getSelfAddresses().subscribe(addresses => this.addresses.next(addresses));
  }

  onAddressSelectionChange(event: MatSelectChange): void {
    const selectedAddress = event.value;
    if (selectedAddress) {
      const addresses: Address[] = this.addresses.getValue();
      const address = addresses.find(a => a.name === selectedAddress);
      if (address) {
        this.mapComponent.addMarker(address.latitude, address.longitude);
        this.addressSelected.emit(selectedAddress)
      }
    }
  }

  addNewAddress() {
    //
  }
}
