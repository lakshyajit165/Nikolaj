import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {

  private apiGateway = environment.apigateway;

  constructor(
    private http: HttpClient
  ) { }

  getDetailsTaken(csrmail: string): Observable<object> {
    return  this.http.get<object>(this.apiGateway + `ticket-service/api/v1/tickets/csr/${csrmail}/taken`);
  }
  getDetailsResolved(csrmail: string): Observable<object> {
    return  this.http.get<object>(this.apiGateway + `ticket-service/api/v1/tickets/csr/${csrmail}/solved`);
  }
}
