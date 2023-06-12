import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderHistoryComponent } from './components/order-history/order-history.component';
import { OrderHistoryResolver } from './resolvers/order-history.resolver';

const routes: Routes = [
  {
    path: '',
    component: OrderHistoryComponent,
    resolve: [OrderHistoryResolver],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrderHistoryRoutingModule {}
