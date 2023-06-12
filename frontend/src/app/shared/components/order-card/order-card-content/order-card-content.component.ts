import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-order-card-content',
  templateUrl: './order-card-content.component.html',
  styleUrls: ['./order-card-content.component.scss'],
})
export class OrderCardContentComponent {
  @Input() name!: string;
  @Input() itemsCount!: number;
}
