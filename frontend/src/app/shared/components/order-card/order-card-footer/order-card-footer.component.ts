import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-order-card-footer',
  templateUrl: './order-card-footer.component.html',
  styleUrls: ['./order-card-footer.component.scss'],
})
export class OrderCardFooterComponent {
  @Input() deliveryCost!: number;
  @Input() totalCost!: number;
  @Input() createdOn!: Date;
}
