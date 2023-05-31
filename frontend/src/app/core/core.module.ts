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
import {CoreEffects} from "./store/core.effects";
import {AdminDeliverersEffects} from "../admin-deliverers/store/admin-deliverers.effects";
import {AdminRestaurantsEffects} from "../admin-restaurants/store/admin-restaurants.effects";

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
    EffectsModule.forRoot([AuthEffects, CoreEffects, AdminDeliverersEffects, AdminRestaurantsEffects]),
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
  ],
  exports: [StoreModule, EffectsModule, StoreDevtoolsModule],
})
export class CoreModule {}
