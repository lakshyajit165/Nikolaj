import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CsrreliabilityComponent } from '../csrreliability/csrreliability.component';
import { ChartsModule } from 'ng2-charts';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material';
import { CSRReliabilityRoutingModule } from './csrreliability-routing.module';
import { MatSelectModule } from '@angular/material/select';
import { MaterialModule } from '../material/material.module';

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
    MatInputModule,
    MatSelectModule,
    MaterialModule
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
 ]
})
export class CsrreliabilityModule { }