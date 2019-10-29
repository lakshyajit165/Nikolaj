import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';

import { TicketdetailsComponent } from './ticketdetails/ticketdetails.component';
import { TicketdetailcardComponent, DialogOverviewExampleDialog } from './ticketdetailcard/ticketdetailcard.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ChatComponent } from './chat/chat.component';
import { OpenticketComponent } from './openticket/openticket.component';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';
import { HomeComponent } from './home/home.component';
import { CommandlistComponent } from './commandlist/commandlist.component';
import { PerformanceComponent } from './performance/performance.component';

import { HttpClientModule } from '@angular/common/http';
import { SuggestionComponent } from './suggestion/suggestion.component';
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuthModule } from '@angular/fire/auth';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth/auth.service';
import { CsrService } from './auth/csr.service';
import { CsrResolver } from './auth/csr.resolver';
import { AuthGuard } from './auth/auth.guard';
import { CommandModule } from 'my-command-library';

import { ChartsModule } from 'ng2-charts';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    TicketdetailsComponent,
    TicketdetailcardComponent,
    PagenotfoundComponent,
    ChatComponent,
    OpenticketComponent,
    LoginComponent,
    HomeComponent,
    CommandlistComponent,
    PerformanceComponent,
    SuggestionComponent,
    DialogOverviewExampleDialog
  ],
  entryComponents: [DialogOverviewExampleDialog ], // add command details dialog here too
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CommandModule,
    ChartsModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule, // imports firebase/firestore, only needed for database features
    AngularFireAuthModule
  ],
  providers: [AuthService, CsrService, CsrResolver, AuthGuard, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
