import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CommandlistComponent } from './commandlist/commandlist.component';
import { PerformanceComponent } from './performance/performance.component';
import { OpenticketComponent } from './openticket/openticket.component';
import { TicketdetailsComponent } from './ticketdetails/ticketdetails.component';
import { AuthGuard } from './auth/auth.guard';
import { CsrResolver } from './auth/csr.resolver';


const routes: Routes = [
  {
    path: 'csrui', redirectTo: '/login', pathMatch: 'full'
  },
  {
    path: 'login', component: LoginComponent, canActivate: [AuthGuard]
  },
  {
    path: 'home', component: HomeComponent, children: [
      {
        path: 'openticket', component: OpenticketComponent, resolve: { data: CsrResolver }
      },
      {
        path: 'commandlist', component: CommandlistComponent, resolve: { data: CsrResolver }
      },
      {
        path: 'performance', component: PerformanceComponent, resolve: { data: CsrResolver }
      },
      {
        path: 'ticket/:id', component: TicketdetailsComponent, resolve: { data: CsrResolver }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
