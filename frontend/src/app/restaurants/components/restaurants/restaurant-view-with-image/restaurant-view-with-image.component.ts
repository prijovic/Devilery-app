import { Component, Inject, Input, OnInit } from '@angular/core';
import { Restaurant } from '../../../../shared/model/restaurant.model';
import { SafeUrl } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';
import { PictureService } from '../../../../services/picture.service';

@Component({
  selector: 'app-restaurant-view-with-image',
  templateUrl: './restaurant-view-with-image.component.html',
  styleUrls: ['./restaurant-view-with-image.component.scss'],
})
export class RestaurantViewWithImageComponent implements OnInit {
  @Input() restaurant!: Restaurant;
  image!: SafeUrl;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private pictureService: PictureService
  ) {}

  ngOnInit(): void {
    if (this.restaurant && this.restaurant.picture)
      this.pictureService
        .getPicture(this.restaurant.picture)
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
}
