import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DelivererActiveOrdersContainerComponent } from './components/deliverer-active-orders-container/deliverer-active-orders-container.component';
import { DelivererActiveOrdersComponent } from './components/deliverer-active-orders-container/deliverer-active-orders/deliverer-active-orders.component';
import { ActiveOrdersResolver } from './resolvers/active-orders.resolver';

const routes: Routes = [
  {
    path: '',
    component: DelivererActiveOrdersContainerComponent,
    children: [
      {
        path: 'all',
        component: DelivererActiveOrdersComponent,
        resolve: [ActiveOrdersResolver],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DelivererActiveOrdersRoutingModule {}
