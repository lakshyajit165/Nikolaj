import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BotRealiabilityComponent } from './botreliability.component';

const routes: Routes = [{ path: '', component: BotRealiabilityComponent}];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class BotReliabilityRoutingModule { }