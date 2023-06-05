import {AfterViewInit, Component} from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit {
  private map: any;
  private markerGroup!: L.LayerGroup;
  private readonly defaultZoom: number = 14;

  ngAfterViewInit(): void {
    this.initMap();
  }

  private initMap(): void {
    this.map = L.map('map').setView([45.267136, 19.833549], this.defaultZoom);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(this.map);
    this.markerGroup = L.layerGroup().addTo(this.map);
  }

  addMarker(lat: number, lng: number): void {
    const markerIcon = L.icon({
      iconUrl: 'path-to-icon-file.png',
      iconSize: [32, 32], // Adjust the size of the icon if needed
      iconAnchor: [16, 32] // Adjust the anchor point of the icon if needed
    });

    this.markerGroup?.clearLayers();

    // const marker = L.marker([lat, lng], { icon: markerIcon }).addTo(this.map);
  }
}
