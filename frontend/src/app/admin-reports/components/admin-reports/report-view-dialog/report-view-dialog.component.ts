import { Component, Inject, OnInit } from '@angular/core';
import { DIALOG_DATA, DialogRef } from '@angular/cdk/dialog';
import { DOCUMENT } from '@angular/common';
import { PictureService } from '../../../../services/picture.service';
import { Store } from '@ngrx/store';
import { Report } from '../../../model/report.model';
import { SafeUrl } from '@angular/platform-browser';
import { resolveReport } from '../../../store/admin-reports.actions';

@Component({
  selector: 'app-report-view-dialog',
  templateUrl: './report-view-dialog.component.html',
  styleUrls: ['./report-view-dialog.component.scss'],
})
export class ReportViewDialogComponent implements OnInit {
  customerImage!: SafeUrl;
  delivererImage!: SafeUrl;

  constructor(
    public dialogRef: DialogRef<string>,
    @Inject(DIALOG_DATA)
    public data: {
      report: Report;
    },
    private store: Store,
    @Inject(DOCUMENT) private document: Document,
    private pictureService: PictureService
  ) {}

  get avatarImageUrl() {
    return 'url("../../../../../assets/images/avatar-grey.png")';
  }

  ngOnInit() {
    if (this.data.report) {
      if (this.data.report.customer.profilePicture)
        this.pictureService
          .getPicture(this.data.report.customer.profilePicture)
          .subscribe((profilePicture) => {
            if (document.defaultView && profilePicture.size > 0) {
              this.customerImage =
                document.defaultView.URL.createObjectURL(profilePicture);
            }
          });
      if (this.data.report.deliverer.profilePicture)
        this.pictureService
          .getPicture(this.data.report.deliverer.profilePicture)
          .subscribe((profilePicture) => {
            if (document.defaultView && profilePicture.size > 0) {
              this.delivererImage =
                document.defaultView.URL.createObjectURL(profilePicture);
            }
          });
    }
  }

  getBackgroundImageUrl(image: SafeUrl) {
    return `url("${image}")`;
  }

  rejectReport() {
    if (this.data.report) {
      this.store.dispatch(
        resolveReport({ id: this.data.report.id, status: 'REJECTED' })
      );
      this.dialogRef.close();
    }
  }

  acceptReport() {
    if (this.data.report) {
      this.store.dispatch(
        resolveReport({ id: this.data.report.id, status: 'ACCEPTED' })
      );
      this.dialogRef.close();
    }
  }
}
