import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { Report } from '../model/report.model';

@Injectable({
  providedIn: 'root',
})
export class AdminReportsHttpService {
  private GET_UNRESOLVED_REPORTS = 'report/pending';
  private RESOLVE_REPORT = (id: string) => `report/${id}/status`;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getUnresolvedReports() {
    return this.http.get<Report[]>(
      this.config.apiEndpoint + this.GET_UNRESOLVED_REPORTS
    );
  }

  resolveReport(id: string, status: string) {
    return this.http.put(
      this.config.apiEndpoint + this.RESOLVE_REPORT(id),
      {},
      {
        params: new HttpParams().append('status', status),
      }
    );
  }
}
