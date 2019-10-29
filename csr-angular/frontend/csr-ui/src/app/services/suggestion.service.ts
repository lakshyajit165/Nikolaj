import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { tick } from '@angular/core/testing';
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
   const uri: string = this.apiGateWay + 'optimus/api/v1/suggestions?id=' + id;
   return  this.http.get<Response>(uri);
  }
  feedback(intent: string, command: string, rating: number): Observable<object> {
   const uri = this.apiGateWay + 'optimus/api/v1/confidence';
   return  this.http.patch<object>(uri,
    {
      intentName: intent,
      commandName: command,
      rating,
    });
  }

  nointent(nointent: NoIntent) {
    const uri = 'http://localhost:8087/optimus/api/v1/report';

    return this.http.patch<object>(uri, nointent);
   }
}
