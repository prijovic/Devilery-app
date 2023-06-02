import {Component, Inject, Input, OnInit} from '@angular/core';
import {RestaurantItem} from "../../../../../model/restaurant-item.model";
import {SafeUrl} from "@angular/platform-browser";
import {DOCUMENT} from "@angular/common";
import {PictureService} from "../../../../../../services/picture.service";

@Component({
  selector: 'app-restaurant-item-card',
  templateUrl: './restaurant-item-card.component.html',
  styleUrls: ['./restaurant-item-card.component.scss']
})
export class RestaurantItemCardComponent implements OnInit {
  @Input() restaurantItem!: RestaurantItem;
  @Input() isUserLoggedIn = false;
  image!: SafeUrl;

  constructor(@Inject(DOCUMENT) private document: Document, private pictureService: PictureService) {}

  ngOnInit(): void {
    if (this.restaurantItem && this.restaurantItem.picture)
      this.pictureService.getPicture(this.restaurantItem.picture).subscribe(profilePicture => {
          if (document.defaultView && profilePicture.size > 0) {
            this.image = document.defaultView.URL.createObjectURL(profilePicture);
          }
        }
      );
  }
  getBackgroundImageUrl() {
    return `url("${this.image}")`;
  }
}
