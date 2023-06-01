import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-restaurant-type-card',
  templateUrl: './restaurant-type-card.component.html',
  styleUrls: ['./restaurant-type-card.component.scss']
})
export class RestaurantTypeCardComponent {
  @Input() image!: string;
  @Input() name!: string;

  getBackgroundImageUrl() {
    return `url("${this.image}")`;
  }
}
