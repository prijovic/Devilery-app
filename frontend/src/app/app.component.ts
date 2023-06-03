import { Component, OnInit } from '@angular/core';
import { autoLogin } from './auth/store/auth.actions';
import { Store } from '@ngrx/store';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(
    private store: Store,
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) {
    matIconRegistry.addSvgIcon(
      'eye-crossed',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/eye-crossed.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'eye',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/eye.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'delivery',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/delivery.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'star',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/star.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'star-grey',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/star-grey.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'time',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/time.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'bag',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/icons/paper-bag.svg'
      )
    );
  }

  ngOnInit(): void {
    this.store.dispatch(autoLogin());
  }
}
