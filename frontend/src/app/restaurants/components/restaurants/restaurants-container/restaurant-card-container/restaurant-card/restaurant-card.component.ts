import {Component} from '@angular/core';
import {
  RestaurantViewWithImageComponent
} from "../../../restaurant-view-with-image/restaurant-view-with-image.component";

@Component({
  selector: 'app-restaurant-card',
  templateUrl: './restaurant-card.component.html',
  styleUrls: ['./restaurant-card.component.scss']
})
export class RestaurantCardComponent extends RestaurantViewWithImageComponent {
}
