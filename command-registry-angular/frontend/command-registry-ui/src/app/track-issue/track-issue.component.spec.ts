import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackIssueComponent } from './track-issue.component';

import { MaterialModule } from '../material/material.module';
import { FormsModule } from '@angular/forms';
import { IssueFilterPipe } from '../pipes/issue-filter.pipe';
import { IntentFilterPipe } from '../pipes/intent-filter.pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('TrackIssueComponent', () => {
  let component: TrackIssueComponent;
  let fixture: ComponentFixture<TrackIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({

      declarations: [ TrackIssueComponent, IssueFilterPipe,
        IntentFilterPipe ],
      imports: [MaterialModule, FormsModule, NgxPaginationModule, HttpClientModule, BrowserAnimationsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackIssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
