import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { TrackIssueComponent } from './track-issue.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('TrackIssueComponent', () => {
  let component: TrackIssueComponent;
  let fixture: ComponentFixture<TrackIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({

      declarations: [ TrackIssueComponent],
      imports: [MaterialModule, FormsModule, HttpClientTestingModule, BrowserAnimationsModule]
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
