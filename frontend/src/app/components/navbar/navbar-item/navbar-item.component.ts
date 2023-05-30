import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-navbar-item',
  templateUrl: './navbar-item.component.html',
  styleUrls: ['./navbar-item.component.scss'],
})
export class NavbarItemComponent {
  @Input() text!: string;
  @Input() url!: string;
}
