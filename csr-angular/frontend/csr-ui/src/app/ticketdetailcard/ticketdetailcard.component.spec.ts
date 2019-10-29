import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketdetailcardComponent } from './ticketdetailcard.component';

describe('TicketdetailcardComponent', () => {
  let component: TicketdetailcardComponent;
  let fixture: ComponentFixture<TicketdetailcardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketdetailcardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketdetailcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
