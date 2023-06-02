import {Component} from '@angular/core';
import {RestaurantViewWithImageComponent} from "../../restaurant-view-with-image/restaurant-view-with-image.component";

@Component({
  selector: 'app-restaurant-details-container',
  templateUrl: './restaurant-details-container.component.html',
  styleUrls: ['./restaurant-details-container.component.scss']
})
export class RestaurantDetailsContainerComponent extends RestaurantViewWithImageComponent {

}
