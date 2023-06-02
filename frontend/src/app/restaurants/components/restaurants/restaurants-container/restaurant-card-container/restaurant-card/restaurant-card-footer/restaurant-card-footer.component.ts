import {Component, Input, OnInit} from '@angular/core';
import {AddressService} from "../../../../../../../shared/services/address.service";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-restaurant-card-footer',
  templateUrl: './restaurant-card-footer.component.html',
  styleUrls: ['./restaurant-card-footer.component.scss']
})
export class RestaurantCardFooterComponent implements OnInit {
  @Input() isClosed!: boolean;
  @Input() rating!: number;
  @Input() restaurantLon!: number;
  @Input() restaurantLat!: number;
  @Input() opensAt!: string;
  @Input() closesAt!: string;
  deliveryCost = new BehaviorSubject<number>(0);

  constructor(private addressService: AddressService) {}

  ngOnInit(): void {
    this.calculateDeliveryCost();
  }

  calculateDeliveryCost() {
    this.getUserLocation().then(location => {
      if (location.latitude && location.longitude) {
        this.addressService.getAddressDistance(location.longitude, location.latitude, this.restaurantLon, this.restaurantLat).subscribe(value => {
          this.deliveryCost.next(value * 1.1);
        });
      }
    });
  }

  async getUserLocation(): Promise<{ longitude: number | null; latitude: number | null }> {
    return new Promise((resolve) => {
      const location: { longitude: number | null; latitude: number | null } = {
        longitude: null,
        latitude: null
      };
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            location.latitude = position.coords.latitude;
            location.longitude = position.coords.longitude;
            resolve(location);
          },
          () => {
            resolve(location);
          }
        );
      } else {
        console.log('Geolocation is not supported by this browser.');
        resolve(location);
      }
    });
  }
}

