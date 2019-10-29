import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CsrreliabilityComponent } from '../csrreliability/csrreliability.component';
import { ChartsModule } from 'ng2-charts';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material';
import { CSRReliabilityRoutingModule } from './csrreliability-routing.module';

@NgModule({
  declarations: [CsrreliabilityComponent],
  imports: [
    CommonModule,
    CSRReliabilityRoutingModule,
    ChartsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule
  ]
})
export class CsrreliabilityModule { }