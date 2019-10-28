import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { ChartsModule } from 'ng2-charts';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from './material/material.module';
import { OpenticketsComponent } from './opentickets/opentickets.component';
import { HttpClientModule } from '@angular/common/http';
import { PerformanceComponent } from './performance/performance.component';
import { TicketdetailsComponent } from './ticketdetails/ticketdetails.component';
import { TicketdetailcardComponent, DialogOverviewExampleDialog } from './ticketdetailcard/ticketdetailcard.component';
import { ChatComponent } from './chat/chat.component';
import { PagenotfoundComponent } from '../app/pagenotfound/pagenotfound.component';
import { FilterPipe } from '../app/filters/filter.pipe';
import { SuggestionComponent } from './suggestion/suggestion.component';


import { FlexLayoutModule } from '@angular/flex-layout';


// import { CommandModule } from 'my-command-library';
// import { CommandlistComponent, CommandDetailsDialog } from './commandlist/commandlist.component';



@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    OpenticketsComponent,
    PerformanceComponent,
    TicketdetailsComponent,
    TicketdetailcardComponent,
    ChatComponent,
    PagenotfoundComponent,
    FilterPipe,
    DialogOverviewExampleDialog,
    SuggestionComponent,
    // CommandlistComponent,
    // CommandDetailsDialog
  ],
  entryComponents: [DialogOverviewExampleDialog], // add CommandDetailsDialog
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MaterialModule,
    HttpClientModule,
    ChartsModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
   FlexLayoutModule
    // CommandModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
