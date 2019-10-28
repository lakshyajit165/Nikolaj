import { TestBed, getTestBed } from '@angular/core/testing';
import { ResponseFormatforService, ResponseFormatforReport } from '../interfaces/ResponseFormat';
import { ReportService } from './report.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { IReport } from "../interfaces/IReport";

describe('ReportService', () => {

  let injector: TestBed;
  let service: ReportService;
  let httpMock: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [ReportService],
    })
    injector = getTestBed();
    service = injector.get(ReportService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

// const dummyTicket: IReport = {

 
//     id: '5d8ef5f76fd09656f117cf4e',
//     ticketId: '5d8ef5f76fd09656f117cf4e ',
//     ticketName: ' Get Space rocket ',
//     raisedBy: ' user1 ',
//     type: ' query ',
//     createdOn: ' 2019-09-23T00:00:00.000+0000 ',
//     ticketStatus: ' engaged ',
//     assignedTo: ' csr1 ',
//     assignedTime: ' 2019-09-23T00:00:00.000+0000 ',
//     csrId: ' 5d8ef5f76fd09656f117cf4e ',
//     updatedOn: ' 2019-09-23T00:00:00.000+0000 ',
//     entity: ' ',
//     responseTime: ' 2019-09-23T00:00:00.000+0000 ',
//     intent: ['hello'] ,
//     rating: 5,
//     resolvedBy : ' csr1 ',
//     reopenDate : ' 2019-09-23T00:00:00.000+0000 '


//   };



//   const dummyTicketStatusResponse = {

//     result: [
// {

 
//     id: '5d8ef5f76fd09656f117cf4e',
//     ticketId: '5d8ef5f76fd09656f117cf4e ',
//     ticketName: ' Get Space rocket ',
//     raisedBy: ' user1 ',
//     type: ' query ',
//     createdOn: ' 2019-09-23T00:00:00.000+0000 ',
//     ticketStatus: ' engaged ',
//     assignedTo: ' csr1 ',
//     assignedTime: ' 2019-09-23T00:00:00.000+0000 ',
//     csrId: ' 5d8ef5f76fd09656f117cf4e ',
//     updatedOn: ' 2019-09-23T00:00:00.000+0000 ',
//     entity: ' ',
//     responseTime: ' 2019-09-23T00:00:00.000+0000 ',
//     intent: ['hello'] ,
//     rating: 5,
//     resolvedBy : ' csr1 ',
//     reopenDate : ' 2019-09-23T00:00:00.000+0000 '


//   }
//     ],
//     errors: false,
//     message: 'Open tickets found!'
//   };

  // Get list of open tickets

  // it('getOpenTickets() should return list of open tickets', () => {
  //   service.getReportsByStatus('',1,10).subscribe((res) => {
  //     expect(res).toEqual(dummyTicketStatusResponse);
  //   });

  //   const req = httpMock.expectOne('http://localhost:9003/api/v1/reports?status=${status}&page=${page}&limit=${limit}');
  //   expect(req.request.method).toBe('GET');
  //   req.flush(dummyTicketStatusResponse);
  // });


  it('should be created', () => {
    const service: ReportService = TestBed.get(ReportService);
    expect(service).toBeTruthy();
  });

});

