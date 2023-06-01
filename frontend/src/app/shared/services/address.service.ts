import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getAddressSearchResults(value: string) {
    const params = new HttpParams()
      .append('text', value)
      .append('format', 'json')
      .append('apiKey', this.config.addressApiKey)
      .append('lang', 'en')
      .append('filter', 'countrycode:rs');
    const headers = new HttpHeaders().append('skip', 'true');
    return this.http.get<{results: {formatted: string, lon: number, lat: number}[]}>(
      this.config.addressApiEndpoint,
      {
        params,
        headers,
      }
    ).pipe(
      map(response => response.results)
    );
  }

  getAddressDistance(lon1: number, lat1: number, lon2: number, lat2: number) {
    const params = new HttpParams()
      .append('waypoints', `${lat1},${lon1}|${lat2},${lon2}`)
      .append('format', 'json')
      .append('mode', 'drive')
      .append('apiKey', this.config.addressApiKey)
      .append('lang', 'en');
    const headers = new HttpHeaders().append('skip', 'true');
    return this.http.get<{results: {distance: number}[]}>(
      this.config.routeApiEndpoint,
      {
        params,
        headers,
      }
    ).pipe(
      map(response => response.results[0].distance / 1000)
    );
  }
}
