import { Component, Inject, Input, OnInit } from '@angular/core';
import { SafeUrl } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';
import { PictureService } from '../../../services/picture.service';
import { Order } from '../../model/order.model';
import { Dialog } from '@angular/cdk/dialog';
import { ReportDialogComponent } from './report-dialog/report-dialog.component';

@Component({
  selector: 'app-order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.scss'],
})
export class OrderCardComponent implements OnInit {
  @Input() order!: Order;
  image!: SafeUrl;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private pictureService: PictureService,
    private dialog: Dialog
  ) {}

  get deliveryCoefficient(): number {
    switch (this.order.deliverer.type) {
      case 'BICYCLE':
        return 6;
      case 'MOTORCYCLE':
        return 2;
      case 'CAR':
        return 3 / 2;
      default:
        return 0;
    }
  }

  get isInHistory(): boolean {
    switch (this.order.status) {
      case 'UNSUCCESSFUL_DELIVERY':
      case 'REJECTED':
      case 'DELIVERED':
        return true;
      default:
        return false;
    }
  }

  get createdOn(): Date {
    return new Date(this.order.createdOn);
  }

  get orderTimeDifference(): number {
    return (this.createdOn.getTime() - Date.now()) / (1000 * 60);
  }

  get minWaitingTime(): number {
    if (this.order) {
      const deliveryTime =
        this.order.deliveryDistance * this.deliveryCoefficient;
      const time = Math.ceil(
        this.orderTimeDifference -
          this.order.restaurant.minPrep +
          deliveryTime +
          1
      );
      return time < 0 ? 0 : time;
    }
    return 0;
  }

  get maxWaitingTime(): number {
    if (this.order) {
      const deliveryTime =
        this.order.deliveryDistance * this.deliveryCoefficient;
      const time = Math.ceil(
        this.orderTimeDifference -
          this.order.restaurant.maxPrep +
          deliveryTime +
          1
      );
      return time < 0 ? 1 : time;
    }
    return 0;
  }

  ngOnInit(): void {
    if (this.order && this.order.restaurant.picture)
      this.pictureService
        .getPicture(this.order.restaurant.picture)
        .subscribe((profilePicture) => {
          if (document.defaultView && profilePicture.size > 0) {
            this.image =
              document.defaultView.URL.createObjectURL(profilePicture);
          }
        });
  }

  getBackgroundImageUrl() {
    return `url("${this.image}")`;
  }

  submitReport() {
    this.dialog.open(ReportDialogComponent, {
      data: { order: this.order },
    });
  }
}
