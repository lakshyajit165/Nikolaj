import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ServiceReportComponent } from './service-report/service-report.component';
import { ServiceOutlierComponent } from './service-outlier/service-outlier.component';
import { BotreliabilityModule } from './botreliability/botreliability.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ReportService } from './services/report.service';

import { ChartsModule } from 'ng2-charts';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';

import { FormsModule } from '@angular/forms';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list'
import { ListModule } from '../app/list/list.module';
import { CsrreliabilityModule } from './csrreliability/csrreliability.module';


@NgModule({
  declarations: [
    AppComponent,
    ServiceReportComponent,
    PageNotFoundComponent,
    NavigationComponent,
    ServiceOutlierComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    ChartsModule,
    HttpClientModule,
    ListModule,
    FlexLayoutModule,
    BotreliabilityModule,
    CsrreliabilityModule,
    
    FormsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,

  ],
  providers: [ReportService],
  bootstrap: [AppComponent]
})
export class AppModule { }
