import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackIssueComponent } from './track-issue.component';

import { MaterialModule } from '../material/material.module';
import { IssueFilterPipe } from '../pipes/issue-filter.pipe';
import { IntentFilterPipe } from '../pipes/intent-filter.pipe';
import { NgxPaginationModule } from 'ngx-pagination';



describe('TrackIssueComponent', () => {
  let component: TrackIssueComponent;
  let fixture: ComponentFixture<TrackIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({

      declarations: [ TrackIssueComponent, IssueFilterPipe,
        IntentFilterPipe ],
      imports: [MaterialModule, NgxPaginationModule]
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
