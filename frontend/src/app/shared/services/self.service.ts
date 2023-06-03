import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient} from "@angular/common/http";
import {Address} from "../model/address.model";

@Injectable({
  providedIn: 'root'
})
export class SelfService {
  GET_PROFILE_PICTURE = "self/profile-picture";
  GET_ADDRESSES = "self/addresses";

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) { }

  getSelfProfilePicture() {
    return this.http.get(this.config.apiEndpoint + this.GET_PROFILE_PICTURE, {responseType: "blob"})
  }

  getSelfAddresses() {
    return this.http.get<Address[]>(this.config.apiEndpoint + this.GET_ADDRESSES);
  }
}
