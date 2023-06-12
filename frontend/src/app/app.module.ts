import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarItemComponent } from './components/navbar/navbar-item/navbar-item.component';
import { UserProfileButtonComponent } from './components/navbar/user-profile-button/user-profile-button.component';
import { OrderBagComponent } from './components/navbar/order-bag/order-bag.component';
import { ActiveOrdersComponent } from './components/navbar/active-orders/active-orders.component';
import { OrdersNotificationsComponent } from './components/navbar/orders-notifications/orders-notifications.component';
import { RestaurantActiveOrdersModule } from './restaurant-active-orders/restaurant-active-orders.module';
import { MenuButtonComponent } from './components/navbar/menu-button/menu-button.component';
import { ReviewDialogComponent } from './components/navbar/active-orders/review-dialog/review-dialog.component';
import { StarRatingModule } from 'angular-star-rating';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    NavbarItemComponent,
    UserProfileButtonComponent,
    OrderBagComponent,
    ActiveOrdersComponent,
    OrdersNotificationsComponent,
    MenuButtonComponent,
    ReviewDialogComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      progressBar: true,
      preventDuplicates: true,
      closeButton: true,
    }),
    StarRatingModule.forRoot(),
    HttpClientModule,
    SharedModule,
    CoreModule,
    RestaurantActiveOrdersModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
