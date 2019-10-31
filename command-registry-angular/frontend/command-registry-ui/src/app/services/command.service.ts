import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ICommand } from '../model/ICommand';
import { ICommandResponse } from '../model/ICommandResponse';
import { catchError, retry } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommandService {
  private commandInfo: ICommand[];

    private url = '/command-registry-angular/assets/data/commands.json';
    //  private url = 'http://localhost:8082/api/v1/commandregistry/commands';

    constructor(private httpClient: HttpClient) { }

    getcommands(): Observable<ICommandResponse> {
       return this.httpClient.get<ICommandResponse>(this.url)
       .pipe(
        retry(3), // retry a failed request up to 3 times
        catchError(this.errorHandler) // then handle the error
       );
    }

    private errorHandler(errorResponse: HttpErrorResponse) {
      if (errorResponse.error instanceof ErrorEvent) {
        console.error('Client Side Error: ', errorResponse.error.message);
      } else {
        console.error('Server side Error: ', errorResponse);
      }
      return throwError(
        'Something bad happened, please try again later.');
    }

}
