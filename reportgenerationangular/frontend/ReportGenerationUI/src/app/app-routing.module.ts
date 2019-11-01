import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceReportComponent } from './service-report/service-report.component';
import { ServiceOutlierComponent } from './service-outlier/service-outlier.component';
import { EntityOutlierComponent } from './entity-outlier/entity-outlier.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SomethingWentWrongComponent } from './something-went-wrong/something-went-wrong.component';

// import { ListComponent } from './list/list.component';

const routes: Routes = [
  { path:  'reports', redirectTo: '/reports' , pathMatch: 'full'},
  { path:  'serviceoutlier', component:  EntityOutlierComponent},
  {path: 'somethingwentwrong' , component: SomethingWentWrongComponent},
 { path: 'reports',
 loadChildren: () => import('./list/list.module').then(m => m.ListModule) },
  { path: 'botreport',
 loadChildren: () => import('./botreliability/botreliability.module').then(m => m.BotreliabilityModule) },
{ path: 'csrreport',
 loadChildren: () => import('./csrreliability/csrreliability.module').then(m => m.CsrreliabilityModule) },
    {
    path: '**', component: PageNotFoundComponent
  },
];
@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
