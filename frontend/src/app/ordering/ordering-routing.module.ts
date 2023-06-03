import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OrderingComponent} from "./components/ordering/ordering.component";
import {NewOrderComponent} from "./components/ordering/new-order/new-order.component";

const routes: Routes = [
  {
    path: '',
    component: OrderingComponent,
    children: [
      {
        path: 'new',
        component: NewOrderComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderingRoutingModule { }
