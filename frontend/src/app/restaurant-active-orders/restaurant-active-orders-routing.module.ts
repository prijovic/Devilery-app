import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantActiveOrdersComponent } from './components/restaurants-active-orders-container/restaurant-active-orders/restaurant-active-orders.component';
import { ActiveOrdersResolver } from './resolvers/active-orders.resolver';
import { RestaurantsActiveOrdersContainerComponent } from './components/restaurants-active-orders-container/restaurants-active-orders-container.component';

const routes: Routes = [
  {
    path: '',
    component: RestaurantsActiveOrdersContainerComponent,
    children: [
      {
        path: 'all',
        component: RestaurantActiveOrdersComponent,
        resolve: [ActiveOrdersResolver],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RestaurantActiveOrdersRoutingModule {}
