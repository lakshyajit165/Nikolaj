import { Component, OnInit, Input } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { Ticket } from '../model/ticketinterface';
import { Csr } from '../model/csrinterface';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-openticket',
  templateUrl: './openticket.component.html',
  styleUrls: ['./openticket.component.css']
})
export class OpenticketComponent implements OnInit {

  responseObject: object;
  closeTickets: object[];
  engagedTickets: object[];
  tickets: object[];
  tags: [];
  ticket: Ticket;
  csr = {} as Csr;
  result = 'result';
  uuid = 'uuid';
  ticketId: string;
  csrId: string;
  csrMail: string;

  @Input() csrmail: string;

  constructor(
    private ticketService: TicketService,
    private router: Router,
    private ticketSerivce: TicketService,
    private cookie: CookieService
  ) {
    // if (this.router.getCurrentNavigation().extras.state !== undefined) {
    //   console.log(this.router.getCurrentNavigation().extras.state);
    //   this.csrMail = this.router.getCurrentNavigation().extras.state.csrmail;
    // }
  }

  ngOnInit() {
    this.csrMail = this.cookie.get('csrmail');
    // console.log(this.csrMail);
  }

 // pass the currently logged in csr mail into this funciton after authentication is implemented
  assignMeClicked() {
    // this.updateTicketWhenCsrAssigned(this.ticket);
    // this.createCsrWhenAssigned(csrmail, this.ticket.query);
    this.ticketService.assignTicket().subscribe(res => {
      // console.log(res);
      // console.log(res[this.result][this.result]);

      this.ticket = res[this.result][this.result];
      this.ticket.assignedTo = this.csrMail; // assign csrmail here
      // console.log(this.ticket);
      this.updateTicketWhenCsrAssigned(this.ticket);

      // change first param to csr mail after authentication
      this.createCsrWhenAssigned(this.csrMail, this.ticket.query, this.ticket.uuid);

      this.router.navigate([`/home/ticket/${this.ticket.uuid}`], { state: { ticket: this.ticket, csr: this.csr }});

    });
    }

  // update ticket db whenever a csr assigns himself

  updateTicketWhenCsrAssigned(ticket: Ticket) {
    this.ticketService.updateTicketWhenCsrAssigned(ticket, 1).subscribe(res => {
      // console.log(res[this.result]);
      this.ticketId = res[this.result][this.uuid];
    });
  }

  // create a new csr record in csr database, when a csr assigns himself

  createCsrWhenAssigned(csrmail: string, ticketName: string, ticketId: string) {
    this.csr.csrmail = csrmail;
    this.csr.ticketId = ticketId;
    this.ticketService.createCsrWhenAssigned(this.csr, ticketName).subscribe(res => {
      // console.log(res);
      this.csr.uuid = res[this.result][this.uuid];
      // con
      // this.csrId = res[this.result][this.uuid];
    });
  }


}
