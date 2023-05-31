import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomePageComponent,
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./auth/auth.module').then((module) => module.AuthModule),
  },
  {
    path: 'admin/deliverers',
    loadChildren: () =>
      import('./admin-deliverers/admin-deliverers.module').then((module) => module.AdminDeliverersModule),
  },
  {
    path: 'admin/restaurants',
    loadChildren: () =>
      import('./admin-restaurants/admin-restaurants.module').then((module) => module.AdminRestaurantsModule),
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
