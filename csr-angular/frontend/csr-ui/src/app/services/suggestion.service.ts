import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

import { environment } from '../../environments/environment.prod';
import { NoIntent } from '../model/nointent';

@Injectable({
 providedIn: 'root'
})
export class SuggestionService {

  private apiGateWay = environment.apigateway;
  private url = '';
 constructor(private http: HttpClient) { }
 getSuggestion(id: string): Observable<Response> {
   const uri: string = this.apiGateWay + 'botwatson/optimus/api/v1/suggestions?id=' + id;
   return  this.http.get<Response>(uri).pipe(
    catchError(this.handleError)
  );
  }
  feedback(intent: string, command: string, rating: number): Observable<object> {
  
   const uri = this.apiGateWay + 'botwatson/optimus/api/v1/confidence';
   console.log(intent, command, rating);
   return  this.http.patch<object>(uri,
    {
      "intentName": intent,
      "commandName" : command,
      "rating": rating,
    }).pipe(
      catchError(this.handleError)
    );
  }

  nointent(nointent: NoIntent) {
    const uri = this.apiGateWay + 'botwatson/optimus/api/v1/report';

    return this.http.patch<object>(uri, nointent).pipe(
      catchError(this.handleError)
    );
   }

    // for the error handling
  private handleError(errorResponse: HttpErrorResponse) {
    if (errorResponse.error instanceof ErrorEvent) {
      console.error('client side error', errorResponse.error.message);
    } else {
      console.error('server side error', errorResponse);
    }
    return throwError('some problem is occured here.............');
  }

}
