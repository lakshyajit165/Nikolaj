import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { TicketService } from '../services/ticket.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private email: string;
  private password: string;
  private errorMessage: string;

  constructor(
    private router: Router,
    private authService: AuthService,
    private ticketService: TicketService
  ) { }

  ngOnInit() {
  }

  login() {
    this.authService.doLogin(this.email, this.password)
    .then(res => {
      console.log(res.user.email);
       // pass the csr mail on succesful login here
      this.router.navigate(['/home/openticket'], { state: { csrmail: res.user.email }});
      this.ticketService.setCsrMail(res.user.email);

    }, err => {
      console.log(err);
      this.errorMessage = err.message;
    });
  }

}
