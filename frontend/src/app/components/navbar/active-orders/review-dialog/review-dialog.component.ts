import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { textValidator } from '../../../../shared/validators/text.validator';
import { DIALOG_DATA, DialogRef } from '@angular/cdk/dialog';
import { SafeUrl } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';
import { PictureService } from '../../../../services/picture.service';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectActiveOrderById } from '../../../../ordering/store/ordering.selectors';
import { Restaurant } from '../../../../shared/model/restaurant.model';
import { Deliverer } from '../../../../shared/model/deliverer.model';
import { submitReview } from '../../../../ordering/store/ordering.actions';

@Component({
  selector: 'app-review-dialog',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.scss'],
})
export class ReviewDialogComponent implements OnInit, OnDestroy {
  formGroup: FormGroup<{
    restaurantRate: FormControl;
    restaurantComment: FormControl;
    delivererRate: FormControl;
    delivererComment: FormControl;
  }> = new FormGroup({
    restaurantRate: new FormControl(null),
    restaurantComment: new FormControl(null, [textValidator]),
    delivererRate: new FormControl(null),
    delivererComment: new FormControl(null, [textValidator]),
  });
  restaurant!: Restaurant;
  deliverer!: Deliverer;
  restaurantImage!: SafeUrl;
  delivererImage!: SafeUrl;
  private storeSubscription!: Subscription;

  constructor(
    public dialogRef: DialogRef<string>,
    @Inject(DIALOG_DATA)
    public data: {
      orderId: string;
    },
    @Inject(DOCUMENT) private document: Document,
    private pictureService: PictureService,
    private store: Store
  ) {}

  get avatarImageUrl() {
    return 'url("../../../../../assets/images/avatar-grey.png")';
  }

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectActiveOrderById(this.data.orderId))
      .subscribe((order) => {
        if (order) {
          this.restaurant = order.restaurant;
          this.deliverer = order.deliverer;
          if (this.restaurant.picture)
            this.pictureService
              .getPicture(this.restaurant.picture)
              .subscribe((profilePicture) => {
                if (document.defaultView && profilePicture.size > 0) {
                  this.restaurantImage =
                    document.defaultView.URL.createObjectURL(profilePicture);
                }
              });
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
      });
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }

  confirm() {
    if (this.formGroup.valid) {
      const orderId = this.data.orderId;
      const delivererRating = this.formGroup.controls.delivererRate.value;
      const delivererComment = this.formGroup.controls.delivererComment.value;
      const restaurantRating = this.formGroup.controls.restaurantRate.value;
      const restaurantComment = this.formGroup.controls.restaurantComment.value;
      this.store.dispatch(
        submitReview({
          orderId,
          delivererRating,
          delivererComment,
          restaurantRating,
          restaurantComment,
        })
      );
      this.dialogRef.close();
    }
  }

  getBackgroundImageUrl(image: SafeUrl) {
    return `url("${image}")`;
  }
}
