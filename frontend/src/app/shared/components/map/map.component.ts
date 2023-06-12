import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectSelectedAddress } from '../../../ordering/store/ordering.selectors';
import { icon, latLng, Layer, marker, tileLayer } from 'leaflet';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss'],
})
export class MapComponent implements OnInit, OnDestroy {
  center = latLng(45.267136, 19.833549);
  options = {
    layers: [
      tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 18,
      }),
    ],
    zoom: 15,
    center: latLng(45.267136, 19.833549),
  };
  markerGroup: Layer[] = [];
  private storeSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectSelectedAddress)
      .subscribe((address) => {
        if (address) {
          this.center = latLng(address.latitude, address.longitude);
          const markerLayer = marker(this.center, {
            icon: icon({
              iconUrl: 'assets/icons/pin.svg',
              iconSize: [48, 48], // Adjust the size of the icon if needed
              iconAnchor: [24, 48],
            }),
          });
          this.markerGroup = [markerLayer];
        }
      });
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }
}
