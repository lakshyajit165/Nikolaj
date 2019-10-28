import { TestBed } from '@angular/core/testing';

import { TrackIssueService } from './track-issue.service';

describe('TrackIssueService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrackIssueService = TestBed.get(TrackIssueService);
    expect(service).toBeTruthy();
  });
});
