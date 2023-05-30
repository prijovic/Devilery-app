import { NgModule } from '@angular/core';
import { AuthComponent } from './components/auth/auth.component';
import { SharedModule } from '../shared/shared.module';
import { AuthRoutingModule } from './auth-routing.module';
import { StoreModule } from '@ngrx/store';
import * as fromAuth from './store/auth.reducer';
import { LoginFormComponent } from './components/auth/login-form/login-form.component';
import { SignUpFormComponent } from './components/auth/sign-up-form/sign-up-form.component';

@NgModule({
  declarations: [AuthComponent, LoginFormComponent, SignUpFormComponent],
  imports: [
    AuthRoutingModule,
    SharedModule,
    StoreModule.forFeature('auth', fromAuth.reducer),
  ],
})
export class AuthModule {}
