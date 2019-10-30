import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { SomethingWentWrongComponent } from './something-went-wrong.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('SomethingWentWrongComponent', () => {
  let component: SomethingWentWrongComponent;
  let fixture: ComponentFixture<SomethingWentWrongComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule],
      declarations: [ SomethingWentWrongComponent ],
      schemas: [
  NO_ERRORS_SCHEMA
]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SomethingWentWrongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
