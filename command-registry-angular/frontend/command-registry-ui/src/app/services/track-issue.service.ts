import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { HttpErrorResponse } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
// import { IReportResponse } from '../model/IReportResponse';

@Injectable({
  providedIn: 'root'
})
export class TrackIssueService {

  private apiGatewayUrl = environment.apiGatewayUrl;
  private intentCommandMappingUrl = environment.intentCommandMappingUrl;
  private reportUrl = environment.reportUrl;
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
      return this.httpClient.post(this.apiGatewayUrl + this.intentCommandMappingUrl, intentName);
    }
    getReportsByType(type: string): Observable<object> {
      return this.httpClient.get<object>(this.apiGatewayUrl + this.reportUrl + type);
    }
}
