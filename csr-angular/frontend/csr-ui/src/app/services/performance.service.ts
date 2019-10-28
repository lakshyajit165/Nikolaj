import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {

  private apiGateWay = environment.apigateway;

  constructor(
    private http: HttpClient
  ) { }

  getDetailsTaken(): Observable<object> {
    return  this.http.get<object>(this.apiGateWay+'ticket-service/tickets/csr/taken')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
   }

   getDetailsResolved(): Observable<object> {
    return  this.http.get<object>(this.apiGateWay+'ticket-service/tickets/csr/resolved')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
   }

   handleError(error): Observable<object> {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    // window.alert(errorMessage);
    return throwError(errorMessage);
  }

}
