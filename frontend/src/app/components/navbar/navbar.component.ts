import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectRole } from '../../auth/store/auth.selectors';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { notifyInfo } from '../../core/store/core.actions';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  storeSubscription!: Subscription;
  role: string | null = null;
  private stompClient: any;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store.select(selectRole).subscribe((role) => {
      this.role = role;
      if (role === 'ADMIN') {
        this.initializeWebSocketConnection();
      }
    });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  initializeWebSocketConnection() {
    const ws = new SockJS('http://localhost:8080/api/socket');
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    this.stompClient.connect({}, () => {
      this.openOrderStatusSocket();
    });
  }

  openOrderStatusSocket() {
    this.stompClient.subscribe(
      '/topic/user-blocked',
      (message: { body: string }) => {
        this.store.dispatch(
          notifyInfo({
            message: message.body,
            title: 'User Blocked',
          })
        );
      }
    );
  }
}
