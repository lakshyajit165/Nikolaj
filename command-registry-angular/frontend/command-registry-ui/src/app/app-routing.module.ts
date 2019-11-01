import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SidenavComponent } from './sidenav/sidenav.component';
import { CommandListComponent } from './command-list/command-list.component';
import { TrackIssueComponent } from './track-issue/track-issue.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { DevResolver } from './auth/dev.resolver';
import { HomeComponent } from './home/home.component';
import { NocommandmappedComponent } from './nocommandmapped/nocommandmapped.component';




// const routes: Routes = [
//   { path: 'command-registry-angular', redirectTo: '/commandlist', pathMatch: 'full'},
//   { path: 'sidenav', component: SidenavComponent },
//   { path: 'commandlist', component: CommandListComponent },
//   { path: 'trackissue', component: TrackIssueComponent },
//   { path: '**', component: PagenotfoundComponent }
// ];

const routes: Routes = [
  { path: 'command-registry-angular', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent, canActivate:  [AuthGuard] },
  { path: 'home', component: HomeComponent, children: [
      { path: 'commandlist', component: CommandListComponent, resolve: { data: DevResolver } },
      { path: 'trackissue', component: TrackIssueComponent, resolve: { data: DevResolver } }
    ]
  },
  { path: 'nomapped', component: NocommandmappedComponent  },
  { path: '**', component: PagenotfoundComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

 }
