import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_CONFIG, APP_SERVICE_CONFIG } from '../app-config/app-config';
import { StoreModule } from '@ngrx/store';
import { reducers } from './store/core.reducer';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../../environments/environment.prod';
import { AuthEffects } from '../auth/store/auth.effects';
import { AuthInterceptor } from '../auth/auth.interceptor';
import { ErrorInterceptor } from './error.interceptor';
import { CoreEffects } from './store/core.effects';
import { AdminDeliverersEffects } from '../admin-deliverers/store/admin-deliverers.effects';
import { AdminRestaurantsEffects } from '../admin-restaurants/store/admin-restaurants.effects';
import { RestaurantsEffects } from '../restaurants/store/restaurants.effects';
import { OrderingEffects } from '../ordering/store/ordering.effects';
import { RestaurantActiveOrdersEffects } from '../restaurant-active-orders/store/restaurant-active-orders.effects';
import { DelivererActiveOrdersEffects } from '../deliverer-active-orders/store/deliverer-active-orders.effects';
import { OrderHistoryEffects } from '../order-history/store/order-history.effects';
import { AdminReportsEffects } from '../admin-reports/store/admin-reports.effects';

@NgModule({
  providers: [
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_CONFIG,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true,
    },
  ],
  imports: [
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot([
      AuthEffects,
      CoreEffects,
      RestaurantsEffects,
      AdminDeliverersEffects,
      AdminRestaurantsEffects,
      OrderingEffects,
      RestaurantActiveOrdersEffects,
      DelivererActiveOrdersEffects,
      OrderHistoryEffects,
      AdminReportsEffects,
    ]),
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
  ],
  exports: [StoreModule, EffectsModule, StoreDevtoolsModule],
})
export class CoreModule {}
