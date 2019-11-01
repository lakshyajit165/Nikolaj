import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/auth';
import { DevService } from './dev.service';


@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    public afAuth: AngularFireAuth,
    public devService: DevService,
    private router: Router
  ) {}

  canActivate(): Promise<boolean> {
    return new Promise((resolve, reject) => {
      this.devService.getCurrentUser()
      .then(user => {
        this.router.navigate(['/home/commandlist']);
        return resolve(false);
      }, err => {
        return resolve(true);
      });
    });
  }
}
