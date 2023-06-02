import {Component, Input} from '@angular/core';
import {Restaurant} from "../../../../../model/restaurant.model";

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.scss']
})
export class RestaurantDetailsComponent {
  @Input() restaurant!: Restaurant;
}
