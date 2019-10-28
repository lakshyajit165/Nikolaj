import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketdetailsComponent } from './ticketdetails.component';
import { TicketdetailcardComponent } from '../ticketdetailcard/ticketdetailcard.component';
import { ChatComponent } from '../chat/chat.component';
import { MaterialModule } from '../material/material.module';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';





describe('TicketdetailsComponent', () => {
  let component: TicketdetailsComponent;
  let fixture: ComponentFixture<TicketdetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MaterialModule, RouterTestingModule, HttpClientTestingModule ],
      declarations: [ TicketdetailsComponent, TicketdetailcardComponent, ChatComponent ],
      schemas: [
        NO_ERRORS_SCHEMA
    ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
