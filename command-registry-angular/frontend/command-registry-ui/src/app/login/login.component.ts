import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

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
    private cookie: CookieService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  login() {
    this.authService.doLogin(this.email, this.password)
    .then(res => {
      console.log(res.user.email);
       // pass the csr mail on succesful login here and create a cookie
      this.cookie.set('devmail', res.user.email);
      this.router.navigate(['/home/commandlist']);

    }, err => {
      console.log(err);
      this.errorMessage = err.message;
    });
  }


}
