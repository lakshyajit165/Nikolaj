import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { TicketService } from '../services/ticket.service';
import { CookieService } from 'ngx-cookie-service';

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
    private ticketService: TicketService,
    private cookie: CookieService
  ) { }

  ngOnInit() {
  }

  login() {
    this.authService.doLogin(this.email, this.password)
    .then(res => {
       // pass the csr mail on succesful login here and create a cookie
      this.cookie.set('csrmail', res.user.email);
      this.router.navigate(['/home/openticket'], { state: { csrmail: res.user.email }});

    }, err => {
      this.errorMessage = err.message;
    });
  }

}
