import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { MatTableModule } from '@angular/material';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SidenavComponent } from './sidenav/sidenav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommandListComponent, CommandDetailsDialogComponent } from './command-list/command-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { HttpClientModule } from '@angular/common/http';
import { TrackIssueService } from './services/track-issue.service';
import { MaterialModule } from './material/material.module';
import { IssueFilterPipe } from './pipes/issue-filter.pipe';
import { IntentFilterPipe } from './pipes/intent-filter.pipe';
import { CommandFilterPipe } from './pipes/command-filter.pipe';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { CommandService } from './services/command.service';
import { TrackIssueComponent } from './track-issue/track-issue.component';
import { CommandModule } from 'my-command-library';
import * as d3 from 'd3';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { DevService } from './auth/dev.service';
import { DevResolver } from './auth/dev.resolver';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuthModule } from '@angular/fire/auth';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    CommandListComponent,
    TrackIssueComponent,
    IssueFilterPipe,
    IntentFilterPipe,
    CommandFilterPipe,
    PagenotfoundComponent,
    CommandDetailsDialogComponent,
    LoginComponent,
    HomeComponent

  ],
  entryComponents: [CommandDetailsDialogComponent],
  imports: [
    BrowserModule,
    MatTableModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    FlexLayoutModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MaterialModule,
     CommandModule,
     MatProgressBarModule,
     AngularFireModule.initializeApp(environment.firebase),
    AngularFireAuthModule,
    AngularFireModule,
    AngularFirestoreModule
  ],
  providers: [CommandService, TrackIssueService,
    CookieService, AuthGuard, AuthService,
    DevService, DevResolver],
bootstrap: [AppComponent]
})
export class AppModule { }
