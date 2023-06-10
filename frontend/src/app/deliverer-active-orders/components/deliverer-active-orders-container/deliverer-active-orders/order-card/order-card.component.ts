import { Component, Input } from '@angular/core';
import { DelivererOrder } from '../../../../model/deliverer-order.model';

@Component({
  selector: 'app-order-card',
  templateUrl: './order-card.component.html',
  styleUrls: ['./order-card.component.scss'],
})
export class OrderCardComponent {
  @Input() order!: DelivererOrder;
}
