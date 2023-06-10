import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { Observable, of, switchMap, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectRole } from '../store/auth.selectors';

@Injectable({
  providedIn: 'root',
})
export class DelivererGuard implements CanActivate {
  constructor(private store: Store, private router: Router) {}

  canActivate():
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.store.select(selectRole).pipe(
      take(1),
      switchMap((role) => {
        if (role === 'DELIVERER') {
          return of(true);
        }
        return of(this.router.createUrlTree(['/']));
      })
    );
  }
}
