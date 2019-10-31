import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpErrorResponse } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { Report } from '../track-issue/track-issue.component';
import { IReportResponse } from '../model/IReportResponse';

@Injectable({
  providedIn: 'root'
})
export class TrackIssueService {

  private url = environment.trackIssueUrl;
  private intentCommandMappingUrl = environment.intentCommandMappingUrl;
     constructor(private httpClient: HttpClient) { }

    private errorHandler(errorResponse: HttpErrorResponse) {
      if (errorResponse.error instanceof ErrorEvent) {
        console.error('Client Side Error: ', errorResponse.error.message);
      } else {
        console.error('Server side Error: ', errorResponse);
      }
      return throwError(
        'Something bad happened, please try again later.');
    }

    mapIntentCommand(intentName: string, commandName: string) {
      return this.httpClient.post(this.intentCommandMappingUrl, intentName);
    }
    // getReportsByType(type: string , page: number): Observable<IReportResponse> {
    //   this.url = `http://localhost:9003/api/v1/commandregistry/reports/type?reports=${type}&page=${page}`;
    //   console.log('url' + this.url);
    //   return this.httpClient.get<IReportResponse>(`http://localhost:9003/reports/type?type=${type}&page=${page}`);
    // }
    // getReportsByType(type: string): Observable<IReportResponse> {
    //   // url = `http://localhost:9003/api/v1/commandregistry/reports/type?type=${type}`;
    //   this.url = `http://15.206.36.205:8765/api/v1/commandregistry/reports/type?type=${type}`;
    //   return this.httpClient.get<IReportResponse>(this.url);
   // }

}
