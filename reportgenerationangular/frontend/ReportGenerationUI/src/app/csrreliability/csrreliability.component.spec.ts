import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CsrreliabilityComponent } from './csrreliability.component';

describe('CsrreliabilityComponent', () => {
  let component: CsrreliabilityComponent;
  let fixture: ComponentFixture<CsrreliabilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CsrreliabilityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CsrreliabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
