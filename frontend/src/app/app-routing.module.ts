import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './auth/guards/admin.guard';
import { AuthGuard } from './auth/guards/auth.guard';
import { RestaurantGuard } from './auth/guards/restaurant.guard';
import { DelivererGuard } from './auth/guards/deliverer.guard';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'restaurants/all',
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./auth/auth.module').then((module) => module.AuthModule),
  },
  {
    path: 'order-history',
    canActivate: [AuthGuard],
    loadChildren: () =>
      import('./order-history/order-history.module').then(
        (module) => module.OrderHistoryModule
      ),
  },
  {
    path: 'ordering',
    canActivate: [AuthGuard],
    loadChildren: () =>
      import('./ordering/ordering.module').then(
        (module) => module.OrderingModule
      ),
  },
  {
    path: 'restaurant/active-orders',
    canActivate: [RestaurantGuard],
    loadChildren: () =>
      import('./restaurant-active-orders/restaurant-active-orders.module').then(
        (module) => module.RestaurantActiveOrdersModule
      ),
  },
  {
    path: 'deliverer/active-orders',
    canActivate: [DelivererGuard],
    loadChildren: () =>
      import('./deliverer-active-orders/deliverer-active-orders.module').then(
        (module) => module.DelivererActiveOrdersModule
      ),
  },
  {
    path: 'restaurants',
    loadChildren: () =>
      import('./restaurants/restaurants.module').then(
        (module) => module.RestaurantsModule
      ),
  },
  {
    path: 'admin/deliverers',
    canActivate: [AdminGuard],
    loadChildren: () =>
      import('./admin-deliverers/admin-deliverers.module').then(
        (module) => module.AdminDeliverersModule
      ),
  },
  {
    path: 'admin/restaurants',
    canActivate: [AdminGuard],
    loadChildren: () =>
      import('./admin-restaurants/admin-restaurants.module').then(
        (module) => module.AdminRestaurantsModule
      ),
  },
  {
    path: 'admin/reports',
    canActivate: [AdminGuard],
    loadChildren: () =>
      import('./admin-reports/admin-reports.module').then(
        (module) => module.AdminReportsModule
      ),
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
