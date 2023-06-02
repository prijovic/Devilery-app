import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-restaurant-card-content',
  templateUrl: './restaurant-card-content.component.html',
  styleUrls: ['./restaurant-card-content.component.scss']
})
export class RestaurantCardContentComponent {
  @Input() name!: string;
  @Input() description!: string;
  @Input() specializedTypes!: string[];
}
