import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import { Ticket } from '../model/ticketinterface';
import { Csr } from '../model/csrinterface';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private apiGateWay = environment.apigateway;

  data = {
    id: '5daedea07418a22b179491a6',
            uuid: 'f1edcb83-5193-4de8-8ec7-ab576b2e546a',
            query: 'Give my order details',
            intent: [
                'price'
            ],
            raisedBy: 'lakshyajit144@gmail.com',
            createdOn: 1571741344945,
            assignedTime: 1571047005650,
            updatedOn: 1571741344945,
            assignedTo: 'bot',
            rating: 0,
            entity: 'bikes',
            type: 'query',
            status: 'open',
            responseType: 'mail',
            resolvedBy: 'csr2@gmail.com',
            reopenTime: 1571047005650

  };
  constructor(
    private http: HttpClient
  ) { }

 
  assignTicket(): Observable<object> {
    return this.http.get<Map<string, object>>(this.apiGateWay + 'csrservice/api/v1/csr/assign')
    .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // update ticket db whenever a csr assigns himself

  updateTicketWhenCsrAssigned(ticket: Ticket, status: number): Observable<object> {
    console.log(ticket);
   // console.log(`http://localhost:8765/ticket-service/api/v1/tickets/${ticket.id}?status=${status}`);
    return this.http.patch<object>(this.apiGateWay + `ticket-service/api/v1/`
    + `tickets/${ticket.uuid}?status=${status}`, ticket)
    .pipe(
      // retry(1),home
      catchError(this.handleError)
    );
  }


  // create a new CSR record when he assigns himself a ticket

  createCsrWhenAssigned(csr: Csr, ticketName: string) {
    return this.http.post<object>(this.apiGateWay + `csrservice/api/v1/csr?ticketname=${ticketName}`, csr)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  // update csr database when a ticket is resolved/closed
  updateCsrWhenTicketResolved(csr: Csr, csrId: string, ticketId: string) {
    return this.http.patch<object>(this.apiGateWay + `csrservice/api/v1/csr/${csr.uuid}/ticket/${ticketId}?status=3`, csr)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  // update ticket database when a ticket is resolved and closed
  updateAndCloseTicket(ticket: Ticket, status: number, resolvedBy: string, responseType: number) {
    return this.http.patch<object>(
      this.apiGateWay + ` ticket-service/api/v1` +
      `/tickets/${ticket.uuid}?status=${status}&resolvedBy=${resolvedBy}&responseType=${responseType}`,
      ticket)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }















































  // all endpoints below this point need to refactored

  changeTicketStatusToEngaged(ticket: Ticket): Observable<object> {
    // console.log('inside ticketservice');
    return this.http.patch<object>(this.apiGateWay+'ticket-service/api/v1/tickets/status/engaged', ticket)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  resolveTicket(ticket: Ticket): Observable<object> {
    return this.http.patch<object>(this.apiGateWay+'ticket-service/api/v1/tickets/status/resolved', ticket)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  callbackMail(ticket: Ticket): Observable<object> {
    return this.http.patch<object>(this.apiGateWay+'ticket-service/api/v1/tickets/status/callbackmail', ticket)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );

  }

  getClosedTickets(): Observable<object> {
    return this.http.get<Map<string, object>>(this.apiGateWay+'ticket-service/tickets/closed')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEngagedTickets(): Observable<object> {
    return this.http.get<Map<string, object>>(this.apiGateWay+'ticket-service/tickets/engaged')
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
