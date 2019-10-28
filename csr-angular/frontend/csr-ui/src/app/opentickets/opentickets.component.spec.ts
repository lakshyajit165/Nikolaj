import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenticketsComponent } from './opentickets.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FilterPipe } from '../filters/filter.pipe';
import { TicketService } from '../services/ticket.service';
import { Observable, of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';

const responseObject = {
  result: [
    { description : 'Food Spilled',
      usermail : 'user9@gmail.com',
      status : 'open',
      rating : 0,
      timeCreated : new Date('2019-12-22'),
      timeResolved : new Date('2019-12-23'),
      assignMeTime : new Date('2019-12-23'),
      commandsUsed : [ 'info', 'add' ],
      type : 'query',
      solvedBy : 'csr2@gmail.com',
      tags : [ 'receipt', 'invoice' ]
     }
  ],
  errors: false,
  msg: 'Open tickets found!'
};

class MockTicketService {
  getOpenTickets(): Observable<object>  {
    return of(responseObject);
  }
}
describe('OpenticketsComponent', () => {
  let component: OpenticketsComponent;
  let fixture: ComponentFixture<OpenticketsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule ],
      declarations: [ OpenticketsComponent, FilterPipe],
      schemas: [
        NO_ERRORS_SCHEMA
      ],
      providers: [
        {provide: TicketService, useClass: MockTicketService}
      ]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenticketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
