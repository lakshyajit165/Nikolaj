import { TestBed, getTestBed, inject } from '@angular/core/testing';

import { TicketService } from './ticket.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Ticket } from '../model/ticketinterface';

describe('TicketService', () => {

  let injector: TestBed;
  let service: TicketService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [TicketService],
    });

    injector = getTestBed();
    service = injector.get(TicketService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  const dummyTicket: Ticket = {
        id: '5d8ef5f76fd09656f117cf4e',
        description: 'Get Space rocket',
        usermail: 'user13@gmail.com',
        status: 'engaged',
        rating: 0,
        timeCreated: '2019-09-23T00:00:00.000+0000',
        timeResolved: '2019-12-23T00:00:00.000+0000',
        assignMeTime: '2019-09-23T00:00:00.000+0000',
        commandsUsed: ['add'],
        type: 'query',
        solvedBy: 'csr1@gmail.com',
        tags: ['invoice'],
  };



  const dummyTicketStatusResponse = {

    result: [
      {
        assignMeTime: '2019-09-23T00:00:00.000+0000',
        commandsUsed: ['add'],
        description: 'Get Space rocket',
        id: '5d8ef5f76fd09656f117cf4e',
        rating: 0,
        solvedBy: 'csr1@gmail.com',
        status: 'open',
        tags: ['invoice'],
        timeCreated: '2019-09-23T00:00:00.000+0000',
        timeResolved: '2019-12-23T00:00:00.000+0000',
        type: 'query',
        usermail: 'user13@gmail.com'
      }
    ],
    errors: false,
    message: 'Open tickets found!'
  };

  // Get list of open tickets

  it('getOpenTickets() should return list of open tickets', () => {
    service.assignTicket().subscribe((res) => {
      expect(res).toEqual(dummyTicketStatusResponse);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/open');
    expect(req.request.method).toBe('GET');
    req.flush(dummyTicketStatusResponse);
  });

  // Ticket status engaged

  it('changeTicketStatusToEngaged() should return a ticket whose status is engaged', () => {
    service.changeTicketStatusToEngaged(dummyTicket).subscribe((res) => {
      expect(res).toEqual(dummyTicket);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/status/engaged');
    expect(req.request.method).toBe('PATCH');
    req.flush(dummyTicket);
  });

  // Ticket status resolved

  it('resolveTicket() should return a ticket whose status is resolved', () => {
    service.resolveTicket(dummyTicket).subscribe((res) => {
      expect(res).toEqual(dummyTicket);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/status/resolved');
    expect(req.request.method).toBe('PATCH');
    req.flush(dummyTicket);
  });

  // Call back mail

  it('callbackMail() should return a ticket whose status is callback', () => {
    service.callbackMail(dummyTicket).subscribe((res) => {
      expect(res).toEqual(dummyTicket);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/status/callbackmail');
    expect(req.request.method).toBe('PATCH');
    req.flush(dummyTicket);
  });

  // getClosedTickets


  it('getClosedTickets() should return a list of closed tickets', () => {
    service.getClosedTickets().subscribe((res) => {
      expect(res).toEqual(dummyTicketStatusResponse);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/closed');
    expect(req.request.method).toBe('GET');
    req.flush(dummyTicketStatusResponse);
  });

  // getEngagedTickets

  it('getEngagedTickets() should return a list of engaged tickets', () => {
    service.getEngagedTickets().subscribe((res) => {
      expect(res).toEqual(dummyTicketStatusResponse);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/engaged');
    expect(req.request.method).toBe('GET');
    req.flush(dummyTicketStatusResponse);
  });



  // it('should be created', () => {
  //   const service: TicketService = TestBed.get(TicketService);
  //   expect(service).toBeTruthy();
  // });
});
