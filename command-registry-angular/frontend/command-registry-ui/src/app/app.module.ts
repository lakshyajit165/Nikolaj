import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SidenavComponent } from './sidenav/sidenav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { CommandListComponent } from './command-list/command-list.component';

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    CommandListComponent,
    TrackIssueComponent,
    IssueFilterPipe,
    IntentFilterPipe,
    CommandFilterPipe,
    PagenotfoundComponent
  ],
  entryComponents: [],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    FlexLayoutModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MaterialModule,
    CommandModule
  ],
  providers: [CommandService, TrackIssueService],
  bootstrap: [AppComponent]
})
export class AppModule { }
