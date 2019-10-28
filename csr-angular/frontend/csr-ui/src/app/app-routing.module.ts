import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OpenticketsComponent } from './opentickets/opentickets.component';
import { PerformanceComponent } from './performance/performance.component';
import { TicketdetailsComponent } from './ticketdetails/ticketdetails.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
// import { CommandlistComponent } from './commandlist/commandlist.component';

const routes: Routes = [
  {
    path: 'csrui', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: OpenticketsComponent
  },
  {
    path: 'performance', component: PerformanceComponent
  },
  {
    path: 'ticket/:id', component: TicketdetailsComponent
  },
  // {
  //   path: 'commandlist', component: CommandlistComponent
  // },
  {
    path: '**', component: PagenotfoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
