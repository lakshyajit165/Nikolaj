import { TestBed, getTestBed } from '@angular/core/testing';
import { ResponseFormatforService, ResponseFormatforReport } from '../interfaces/ResponseFormat';
import { ReportService } from './report.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { IReport } from '../interfaces/IReport';

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
    });
    injector = getTestBed();
    service = injector.get(ReportService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    // tslint:disable-next-line: no-shadowed-variable
    const service: ReportService = TestBed.get(ReportService);
    expect(service).toBeTruthy();
  });

});

