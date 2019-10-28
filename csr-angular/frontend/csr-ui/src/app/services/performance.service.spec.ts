import { TestBed, getTestBed } from '@angular/core/testing';

import { PerformanceService } from './performance.service';

import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

describe('PerformanceService', () => {

  let injector: TestBed;
  let service: PerformanceService;
  let httpMock: HttpTestingController;

  const dummyResponse = {

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

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [PerformanceService],
    });
    injector = getTestBed();
    service = injector.get(PerformanceService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  // List of tickets taken by a particular csr

  it('getDetailsTaken() should return list of tickets taken by a particular csr', () => {
    service.getDetailsTaken().subscribe((res) => {
      expect(res).toEqual(dummyResponse);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/csr/taken');
    expect(req.request.method).toBe('GET');
    req.flush(dummyResponse);
  });

  // List of tickets resolved by a particular csr

  it('getDetailsresolved() should return list of tickets resolved by a particular csr', () => {
    service.getDetailsResolved().subscribe((res) => {
      expect(res).toEqual(dummyResponse);
    });

    const req = httpMock.expectOne('http://zuul-api-gateway:8765/ticketservice/tickets/csr/resolved');
    expect(req.request.method).toBe('GET');
    req.flush(dummyResponse);
  });


  // it('should be created', () => {
  //   const service: PerformanceService = TestBed.get(PerformanceService);
  //   expect(service).toBeTruthy();
  // });
});
