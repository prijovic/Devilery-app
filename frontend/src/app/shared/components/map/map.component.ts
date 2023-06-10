import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectSelectedAddress } from '../../../ordering/store/ordering.selectors';
import { icon, latLng, LayerGroup, marker, tileLayer } from 'leaflet';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss'],
})
export class MapComponent implements OnInit, OnDestroy {
  options = {
    layers: [
      tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: '...',
      }),
    ],
    zoom: 15,
    center: latLng(45.267136, 19.833549),
  };
  markerGroup: LayerGroup[] = [];
  storeSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectSelectedAddress)
      .subscribe((address) => {
        if (address) {
          const layer = new LayerGroup();
          const pin = marker([address.longitude, address.latitude], {
            icon: icon({
              iconUrl: 'assets/icons/pin.svg',
              iconSize: [48, 48], // Adjust the size of the icon if needed
              iconAnchor: [24, 48],
            }),
          });
          pin.addTo(layer);
          this.markerGroup = [layer];
        }
      });
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }
}
