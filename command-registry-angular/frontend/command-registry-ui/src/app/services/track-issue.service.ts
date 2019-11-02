import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpErrorResponse } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { Report } from '../track-issue/track-issue.component';
import { IReportResponse } from '../model/IReportResponse';
import { IIntentCommand } from '../model/IntentCommand';

@Injectable({
  providedIn: 'root'
})
export class TrackIssueService {
private  intentCommandMappingUrl = environment.intentCommandMappingUrl;

  intentCommand: IIntentCommand;

     constructor(private httpClient: HttpClient) {
       this.intentCommand = new IIntentCommand();
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

    mapIntentCommand(intentName: string, commandName: string) {
      console.log('command name = ' + commandName);
      console.log('intent name = ' + intentName);
      console.log('intentCommand=', this.intentCommand);
      this.intentCommand.commandName = commandName;
      this.intentCommand.intentName.push(intentName);
      // tslint:disable-next-line:no-unused-expression
      this.intentCommand.commandParameter;
      this.intentCommand.relationshipName = '';
      console.log('object sending in the api call = ' + this.intentCommand.commandName);
      this.httpClient.post( this.intentCommandMappingUrl, this.intentCommand).subscribe(res => {
        return res;
      });
    }

}
