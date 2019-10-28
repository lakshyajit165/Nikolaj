import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { Ticket } from '../model/ticketinterface';
import { Csr } from '../model/csrinterface';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-opentickets',
  templateUrl: './opentickets.component.html',
  styleUrls: ['./opentickets.component.css']
})
export class OpenticketsComponent implements OnInit {

  responseObject: object;
  closeTickets: object[];
  engagedTickets: object[];
  tickets: object[];
  tags: [];
  ticket: Ticket;
  csr = {} as Csr;
  result = 'result';


  constructor(
    private ticketService: TicketService,
    private router: Router
  ) {

  }

  ngOnInit() {
    // this.ticketService.assignTicket().subscribe(res => {
    //   console.log(res);
    //   this.responseObject = res;

    //   // this.updateCSR(this.responseObject);
    //   // this.extractData(this.responseObject);
    //   console.log(this.responseObject);

    // },
    // (err) => {
    //   console.log(err);
    // });
  }

 // pass the currently logged in csr mail into this funciton after authentication is implemented
  assignMeClicked(csrmail: string) {
    // this.updateTicketWhenCsrAssigned(this.ticket);
    // this.createCsrWhenAssigned(csrmail, this.ticket.query);
    this.ticketService.assignTicket().subscribe(res => {
      console.log(res);
      console.log(res[this.result][this.result]);

      this.ticket = res[this.result][this.result];
      this.ticket.assignedTo = 'csr1@gmail.com'; // assign csrmail here
      console.log(this.ticket);
      this.updateTicketWhenCsrAssigned(this.ticket);
      this.createCsrWhenAssigned(csrmail, this.ticket.query, this.ticket.uuid);

      this.router.navigate([`/ticket/${this.ticket.uuid}`], { state: { ticket: this.ticket }});

    });
    }

  // update ticket db whenever a csr assigns himself

  updateTicketWhenCsrAssigned(ticket: Ticket) {
    this.ticketService.updateTicketWhenCsrAssigned(ticket, 1).subscribe(res => {
      console.log(res);
    });
  }

  // create a new csr recor in csr database, when a csr assigns himself

  createCsrWhenAssigned(csrmail: string, ticketName: string, ticketId: string) {
    this.csr.csrmail = csrmail;
    this.csr.ticketId = ticketId;
    this.ticketService.createCsrWhenAssigned(this.csr, ticketName).subscribe(res => {
      console.log(res);
    });
  }


}
