import {Component, Input} from '@angular/core';
import {Restaurant} from "../../../model/restaurant.model";

@Component({
  selector: 'app-restaurant-card-container',
  templateUrl: './restaurant-card-container.component.html',
  styleUrls: ['./restaurant-card-container.component.scss']
})
export class RestaurantCardContainerComponent {
  @Input() restaurants: Restaurant[] = [];
}
