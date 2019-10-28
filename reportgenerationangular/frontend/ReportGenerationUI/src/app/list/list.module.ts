import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from '../list/list.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { MaterialModule } from '../material/material.module';
import { ListRoutingModule } from './list-routing.module';


@NgModule({
  declarations: [ListComponent],
  imports: [
    CommonModule,
    InfiniteScrollModule,
    MaterialModule,
    ListRoutingModule
  ]
})
export class ListModule { }