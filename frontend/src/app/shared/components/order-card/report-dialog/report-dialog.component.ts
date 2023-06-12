import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { textValidator } from '../../../validators/text.validator';
import { Deliverer } from '../../../model/deliverer.model';
import { SafeUrl } from '@angular/platform-browser';
import { DIALOG_DATA, DialogRef } from '@angular/cdk/dialog';
import { DOCUMENT } from '@angular/common';
import { PictureService } from '../../../../services/picture.service';
import { Store } from '@ngrx/store';
import { submitReport } from '../../../../order-history/store/order-history.actions';
import { Order } from '../../../model/order.model';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss'],
})
export class ReportDialogComponent implements OnInit {
  formGroup: FormGroup<{
    comment: FormControl;
  }> = new FormGroup({
    comment: new FormControl(null, [textValidator, Validators.required]),
  });
  deliverer!: Deliverer;
  delivererImage!: SafeUrl;

  constructor(
    public dialogRef: DialogRef<string>,
    @Inject(DIALOG_DATA)
    public data: {
      order: Order;
    },
    @Inject(DOCUMENT) private document: Document,
    private pictureService: PictureService,
    private store: Store
  ) {}

  get avatarImageUrl() {
    return 'url("../../../../../assets/images/avatar-grey.png")';
  }

  ngOnInit() {
    this.deliverer = this.data.order.deliverer;
    if (this.deliverer.profilePicture)
      this.pictureService
        .getPicture(this.deliverer.profilePicture)
        .subscribe((profilePicture) => {
          if (document.defaultView && profilePicture.size > 0) {
            this.delivererImage =
              document.defaultView.URL.createObjectURL(profilePicture);
          }
        });
  }

  confirm() {
    if (this.formGroup.valid) {
      const orderId = this.data.order.id;
      const comment = this.formGroup.controls.comment.value;
      this.store.dispatch(
        submitReport({
          orderId,
          comment,
        })
      );
      this.dialogRef.close();
    }
  }

  getBackgroundImageUrl(image: SafeUrl) {
    return `url("${image}")`;
  }
}
