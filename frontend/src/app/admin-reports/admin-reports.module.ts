import { NgModule } from '@angular/core';

import { AdminReportsRoutingModule } from './admin-reports-routing.module';
import { SharedModule } from '../shared/shared.module';
import { StoreModule } from '@ngrx/store';
import * as fromAdminReports from './store/admin-reports.reducer';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';
import { ReportViewDialogComponent } from './components/admin-reports/report-view-dialog/report-view-dialog.component';
import { StarRatingModule } from 'angular-star-rating';

@NgModule({
  declarations: [AdminReportsComponent, ReportViewDialogComponent],
  imports: [
    SharedModule,
    StoreModule.forFeature('admin-reports', fromAdminReports.reducer),
    AdminReportsRoutingModule,
    StarRatingModule,
  ],
})
export class AdminReportsModule {}
