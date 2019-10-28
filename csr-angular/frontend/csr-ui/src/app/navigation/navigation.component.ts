import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { TicketService } from '../services/ticket.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit{

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  private csrmail: string;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router,
    private ticketService: TicketService
    ) {
      // if (this.router.getCurrentNavigation().extras.state !== undefined) {
      //   this.csrmail = this.router.getCurrentNavigation().extras.state.csrmail;
      //   console.log(this.csrmail);
      //   this.ticketService.setCsrMail(this.csrmail);
      // }
  }

  ngOnInit() {
    this.csrmail = this.ticketService.getCsrMail();
  }

}
