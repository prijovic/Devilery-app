import { Component, Inject, Input, OnInit } from '@angular/core';
import { DelivererOrder } from '../../../../model/deliverer-order.model';
import { SafeUrl } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';
import { PictureService } from '../../../../../services/picture.service';
import { ConfirmationDialogComponent } from '../../../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { ConfirmationDialogWithReasonInputComponent } from '../../../../../shared/components/confirmation-dialog-with-reason-input/confirmation-dialog-with-reason-input.component';
import {
  failOrder,
  successOrder,
} from '../../../../store/deliverer-active-orders.actions';
import { Store } from '@ngrx/store';
import { Dialog } from '@angular/cdk/dialog';

@Component({
  selector: 'app-deliverer-order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.scss'],
})
export class OrderCardComponent implements OnInit {
  @Input() order!: DelivererOrder;
  customerImage!: SafeUrl;
  restaurantImage!: SafeUrl;
  isDelivering = false;

  get userAvatarUrl() {
    return 'url("../../../../../../assets/images/avatar-grey.png")';
  }

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private pictureService: PictureService,
    private store: Store,
    private dialog: Dialog
  ) {}

  ngOnInit(): void {
    this.isDelivering = this.order.status === 'BEING_DELIVERED';
    if (this.order.restaurant.picture) {
      this.pictureService
        .getPicture(this.order.restaurant.picture)
        .subscribe((profilePicture) => {
          if (document.defaultView && profilePicture.size > 0) {
            this.restaurantImage =
              document.defaultView.URL.createObjectURL(profilePicture);
          }
        });
    }
    if (this.order.customer.profilePicture) {
      this.pictureService
        .getPicture(this.order.customer.profilePicture)
        .subscribe((profilePicture) => {
          if (document.defaultView && profilePicture.size > 0) {
            this.customerImage =
              document.defaultView.URL.createObjectURL(profilePicture);
          }
        });
    }
  }

  getBackgroundImageUrl(image: SafeUrl) {
    return `url("${image}")`;
  }

  successOrder() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Finish Order',
        text: `Did you successfully delivered order ${this.order.id}?`,
        confirmationButtonText: 'Yes, I did',
        cancelButtonText: "No, I haven't",
      },
    });

    dialogRef.closed.subscribe((answer) => {
      if (answer === 'confirm') {
        this.store.dispatch(successOrder({ id: this.order.id }));
      }
    });
  }

  failOrder() {
    const dialogRef = this.dialog.open(
      ConfirmationDialogWithReasonInputComponent,
      {
        data: {
          title: 'Report Order',
          text: `Something want wrong with delivering order ${this.order.id}? Please describe the situation to us.`,
          confirmationButtonText: 'Yes, is was unsuccessful',
          cancelButtonText: 'No, cancel',
        },
      }
    );

    dialogRef.closed.subscribe((answer) => {
      if (typeof answer === 'string') {
        this.store.dispatch(failOrder({ id: this.order.id, reason: answer }));
      }
    });
  }
}
