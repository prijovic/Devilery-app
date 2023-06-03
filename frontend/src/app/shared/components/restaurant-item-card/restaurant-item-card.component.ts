import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {RestaurantItem} from "../../model/restaurant-item.model";
import {SafeUrl} from "@angular/platform-browser";
import {DOCUMENT} from "@angular/common";
import {PictureService} from "../../../services/picture.service";
import {Store} from "@ngrx/store";
import {addItemToOrder, removeItemFromOrder} from "../../../ordering/store/ordering.actions";
import {OrderingService} from "../../../ordering/services/ordering.service";

@Component({
  selector: 'app-restaurant-item-card',
  templateUrl: './restaurant-item-card.component.html',
  styleUrls: ['./restaurant-item-card.component.scss']
})
export class RestaurantItemCardComponent implements OnInit {
  @Output() itemAddedToOrder = new EventEmitter<RestaurantItem>();
  @Input() restaurantItem!: RestaurantItem;
  @Input() restaurantId!: string;
  @Input() isUserLoggedIn = false;
  image!: SafeUrl;

  constructor(@Inject(DOCUMENT) private document: Document, private pictureService: PictureService, private store: Store, private orderingService: OrderingService) {}

  get itemCountInBag(): number {
    return this.orderingService.getItemCountById(this.restaurantItem.id);
  }

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

  addItemToOrder() {
    this.itemAddedToOrder.emit(this.restaurantItem);
    this.store.dispatch(addItemToOrder({item: this.restaurantItem, restaurantId: this.restaurantId}));
  }

  removeItemFromOrder() {
    this.store.dispatch(removeItemFromOrder({itemId: this.restaurantItem.id}));
  }
}
