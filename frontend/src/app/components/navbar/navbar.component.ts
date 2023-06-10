import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectRole } from '../../auth/store/auth.selectors';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  storeSubscription!: Subscription;
  role: string | null = null;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store.select(selectRole).subscribe((role) => {
      this.role = role;
    });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }
}
