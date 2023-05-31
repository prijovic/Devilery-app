import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {debounceTime, distinctUntilChanged, filter, map, Observable} from "rxjs";
import {AddressService} from "../../services/address.service";

@Component({
  selector: 'app-address-field',
  templateUrl: './address-field.component.html',
  styleUrls: ['./address-field.component.scss']
})
export class AddressFieldComponent implements OnInit {
  @Output() addressSelected: EventEmitter<{formatted: string, lon: number, lat: number}> = new EventEmitter();
  addressGroup: FormGroup<{address: FormControl}> = new FormGroup({
    address: new FormControl(null, [Validators.required])
  });
  addresses$!: Observable<{formatted: string, lon: number, lat: number}[]>;

  constructor(private addressService: AddressService) {}

  ngOnInit() {
    this.initAddressesAutocompleteOnChange();
  }

  private initAddressesAutocompleteOnChange() {
    this.addressGroup.controls['address'].valueChanges
      .pipe(
        debounceTime(25),
        distinctUntilChanged(),
        filter((value: string) => value.length > 0)
      )
      .subscribe((value: string) => {
        this.addresses$ = this.addressService.getAddressSearchResults(value);
      });
  }
}
