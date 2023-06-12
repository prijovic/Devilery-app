import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderingComponent } from './components/ordering/ordering.component';
import { NewOrderComponent } from './components/ordering/new-order/new-order.component';
import { ActiveOrdersComponent } from './components/ordering/active-orders/active-orders.component';
import { ActiveOrdersResolver } from './resolvers/active-orders.resolver';

const routes: Routes = [
  {
    path: '',
    component: OrderingComponent,
    children: [
      {
        path: 'new',
        component: NewOrderComponent,
      },
      {
        path: 'active-orders',
        component: ActiveOrdersComponent,
        resolve: [ActiveOrdersResolver],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrderingRoutingModule {}
