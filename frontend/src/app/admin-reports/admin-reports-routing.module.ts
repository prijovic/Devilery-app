import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';
import { UnresolvedReportsResolver } from './resolvers/unresolved-reports.resolver';

const routes: Routes = [
  {
    path: '',
    component: AdminReportsComponent,
    resolve: [UnresolvedReportsResolver],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminReportsRoutingModule {}
