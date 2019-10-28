import { NgModule } from '@angular/core';
import { MatFormFieldModule, MatExpansionModule, MatSelectModule, MatChipsModule } from '@angular/material';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule } from '@angular/material';
import { MatListModule, MatCardModule, MatMenuModule } from '@angular/material';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDatepickerModule, } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTooltipModule } from '@angular/material/tooltip';



const materialComponents = [
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatGridListModule,
  MatCardModule,
  MatMenuModule,
  MatInputModule,
  MatFormFieldModule,
  MatRadioModule,
  MatSidenavModule,
  MatInputModule,
  MatGridListModule,
  MatButtonModule,
  MatListModule,
  MatToolbarModule,
  MatFormFieldModule,
  MatIconModule,
  MatExpansionModule,
  MatSelectModule,
  MatChipsModule,
  MatProgressBarModule,
  MatRadioModule,
  MatGridListModule,
  MatDatepickerModule,
  MatNativeDateModule,
  FormsModule,
  MatInputModule,
  MatTableModule,
  MatCheckboxModule,
  MatTooltipModule
];

@NgModule({
  declarations: [],
  imports: [
    materialComponents
  ],
  exports: [
    materialComponents
  ]
})
export class MaterialModule { }
