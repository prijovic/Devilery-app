import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../app-config/app-config";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PictureService {

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) { }

  uploadPicture(picture: File) {
    const formData = new FormData();
    formData.append('file', picture);
    return this.http.post<{fileName: string}>(this.config.apiEndpoint + 'picture/', formData);
  }

  deletePicture(pictureName: string) {
    return this.http.delete<void>(this.config.apiEndpoint + "picture/", {
      params: new HttpParams().append("fileName", pictureName)
    });
  }
}
