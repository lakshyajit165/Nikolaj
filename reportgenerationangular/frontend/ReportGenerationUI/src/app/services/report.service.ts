import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';
import { ResponseFormatforService, ResponseFormatforReport, ResponseFormatforSize } from '../interfaces/ResponseFormat';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private apiGateWay = environment.apigateway;

  private listUrl: string;

  constructor(private httpClient: HttpClient) { }

  // service report 1.all time data 2. on the basis of date
  getServiceReport(startdate: string, enddate: string): Observable<ResponseFormatforService> {
    if (startdate === undefined ) {
      startdate = '';

    }

    if (enddate === undefined ) {
      enddate = '';
    }
    this.listUrl = `${this.apiGateWay}reportservice/api/v1/report/service?startdate=${startdate}&enddate=${enddate}`;
    return this.httpClient.get<ResponseFormatforService>(this.listUrl);
  }


  // service report 1.all time data 2. on the basis of date
  getSize(status: string): Observable<ResponseFormatforSize> {
    this.listUrl = `${this.apiGateWay}reportservice/api/v1/reportsize?status=${status}`;
    return this.httpClient.get<ResponseFormatforSize>(this.listUrl);
  }


  // to give the url related to the tickets based on the status
  getReportsByStatus(status: string, page: number, limit: number): Observable<ResponseFormatforReport> {
    this.listUrl = `${this.apiGateWay}reportservice/api/v1/reports?status=${status}&page=${page}&limit=${limit}`;
    if (status === '') {
      status = '';
    }
    return this.httpClient.get<ResponseFormatforReport>(this.listUrl);
  }

  // to get the bot report according to the given date
  getBotReliability(startdate: string, enddate: string): Observable<object> {
    this.listUrl = `${this.apiGateWay}reportservice/api/v1/report/reliablebot?startdate=${startdate}&enddate=${enddate}`;
    return this.httpClient.get<object>(this.listUrl);
  }

  getCsrRelaibility(monthNo: number): Observable<object> {
    this.listUrl = `${this.apiGateWay}reportservice/api/v1/reports/reliablecsr?month=${monthNo}`;
    return this.httpClient.get<object>(this.listUrl);
  }





}

