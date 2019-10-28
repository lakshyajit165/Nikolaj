import { NgModule } from '@angular/core';
import { MatFormFieldModule, MatRadioModule, MatExpansionModule, MatSelectModule, MatChipsModule } from '@angular/material';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatDialogModule } from '@angular/material';
import { MatListModule, MatGridListModule, MatCardModule, MatMenuModule, MatInputModule } from '@angular/material';

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
    MatDialogModule
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
