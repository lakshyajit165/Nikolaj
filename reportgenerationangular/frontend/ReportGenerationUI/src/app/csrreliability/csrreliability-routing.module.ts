import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CsrreliabilityComponent } from './csrreliability.component';

const routes: Routes = [{ path: '', component: CsrreliabilityComponent}];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class CSRReliabilityRoutingModule { }