import { Component, OnDestroy, OnInit } from '@angular/core';
import { Report } from '../../model/report.model';
import { MatTableDataSource } from '@angular/material/table';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectReports } from '../../store/admin-reports.selectors';
import { Dialog } from '@angular/cdk/dialog';
import { ReportViewDialogComponent } from './report-view-dialog/report-view-dialog.component';

@Component({
  selector: 'app-admin-reports',
  templateUrl: './admin-reports.component.html',
  styleUrls: ['./admin-reports.component.scss'],
})
export class AdminReportsComponent implements OnInit, OnDestroy {
  private storeSubscription!: Subscription;
  displayedColumns = ['customer', 'createdOn', 'deliverer', 'comment', 'view'];
  reports: MatTableDataSource<Report> = new MatTableDataSource<Report>([]);

  constructor(private store: Store, private dialog: Dialog) {}

  getDate(date: Date): Date {
    return new Date(date);
  }

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectReports)
      .subscribe((reports) => {
        this.reports = new MatTableDataSource<Report>(reports);
      });
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }

  viewReport(report: Report) {
    this.dialog.open(ReportViewDialogComponent, {
      data: { report },
    });
  }
}
