import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BotRealiabilityComponent } from '../botreliability/botreliability.component';
import { BotReliabilityRoutingModule } from './botreliability-routing.module';
import { ChartsModule } from 'ng2-charts';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material';

@NgModule({
  declarations: [BotRealiabilityComponent],
  imports: [
    CommonModule,
    BotReliabilityRoutingModule,
    ChartsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule
  ],
  schemas:[ CUSTOM_ELEMENTS_SCHEMA ]
})
export class BotreliabilityModule { }
