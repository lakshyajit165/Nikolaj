import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CsrreliabilityComponent } from './csrreliability.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('CsrreliabilityComponent', () => {
  let component: CsrreliabilityComponent;
  let fixture: ComponentFixture<CsrreliabilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule, HttpClientTestingModule ],
            declarations: [ CsrreliabilityComponent ],
            schemas: [
              NO_ERRORS_SCHEMA
            ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CsrreliabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
