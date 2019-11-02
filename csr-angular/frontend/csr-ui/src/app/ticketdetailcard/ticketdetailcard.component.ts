import { Component, OnInit, Inject } from '@angular/core';
import { Ticket } from '../model/ticketinterface';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TicketService } from '../services/ticket.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { environment } from '../../environments/environment.prod';
import { Csr } from '../model/csrinterface';

export interface DialogData {
  reportReason: string;
}

@Component({
  // tslint:disable-next-line: component-selector
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'reportuserdialog.html',
  styleUrls: ['./dialog.css']
})
// tslint:disable-next-line: component-class-suffix
export class DialogOverviewExampleDialog {

  private reportReason;


  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}


@Component({
  selector: 'app-ticketdetailcard',
  templateUrl: './ticketdetailcard.component.html',
  styleUrls: ['./ticketdetailcard.component.css']
})
export class TicketdetailcardComponent implements OnInit {

  private ticket: Ticket;
  private csr: Csr;
  private ticketId: string;
  private csrId: string;
  private csrMail: string;
  private intent: string[];
  private status: string;
  private stat = 'status';
  private result = 'result';
  private command: string;
  private err = 'error';
  private msg = 'message';
  
  private apiGateWay = environment.apigateway;

  constructor(
    private router: Router,
    private http: HttpClient,
    private ticketService: TicketService,
    private snackbar: MatSnackBar,
    public dialog: MatDialog,

  ) {

    if (this.router.getCurrentNavigation().extras.state !== undefined) {
      this.ticket = this.router.getCurrentNavigation().extras.state.ticket;
      this.csr = this.router.getCurrentNavigation().extras.state.csr;
      this.ticketId = this.ticket.uuid;
      this.csrId = this.csr.uuid;
      this.csrMail = this.csr.csrmail;
      this.intent = this.ticket.intent;
      this.status = 'Engaged';

      console.log(this.csr);
      console.log(this.ticket);
      console.log(this.csrMail);
    }

  }
  ngOnInit() {

  }

  // Open the dialog box
  openDialog(func: string, res: object): void {
    // console.log(func);
    let dialogRef;
    // if func is report, then report the user else, show the execute command response
    if (func === 'report') {
      dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        width: '400px',
       data: { title: 'Report User', sub: 'Why you are reporting this user?', report: true }
      });
    } else if (func === 'execute') {
      dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        width: '400px',
       data: { title: 'Command Response',
                sub: 'Following is the response for the command you executed:',
                command: true,
                response: JSON.stringify(res)
              }
      });
    }

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      //console.log(result);
      if(result !== undefined){
        this.ticketService.updateAndCloseTicket(this.ticket, 2, this.csrMail, 3).subscribe(res => {
          this.updateCsrWhenTicketResolved();
          this.snackbar.open("User Reported!", 'Dismiss', { duration: 4000 });
          this.router.navigate(['/home/openticket']);
        })
      }  
    });
  }


 
  resolveTicket(status: number, responseType: number, message: string) {
    this.ticketService.updateAndCloseTicket(this.ticket, status, this.csrMail, responseType).subscribe(res => {
      this.snackbar.open(message, 'Dismiss', { duration: 4000 });
      // console.log(res);
      this.updateCsrWhenTicketResolved();
      this.router.navigate(['/home/openticket']);
    });
  }

  // updateCSRdatabase when he resolves a ticket
  updateCsrWhenTicketResolved() {
    this.ticketService.updateCsrWhenTicketResolved(this.csr, this.csrId, this.ticketId).subscribe(res => {
      // console.log('csr updated');
      // console.log(res);
    });
  }
  
  executeCommand(command: string) {

    this.http.post(`${this.apiGateWay}commandregistry/api/v1/commandregistry/execute/${command}`,
    {csrUserId: 'adawd'}).subscribe(res => {
      // console.log(res);
      if (res[this.err] === 'true') {
        this.openDialog('execute', res[this.result][this.msg]);
      } else {
        this.openDialog('execute', res[this.result]);
      }

    });
  }

}

