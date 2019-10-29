import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { CsrService } from './csr.service';
import { FirebaseUserModel } from './csr.model';

@Injectable()
export class CsrResolver implements Resolve<FirebaseUserModel> {

  constructor(public csrService: CsrService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot): Promise<FirebaseUserModel> {

    const csr = new FirebaseUserModel();

    return new Promise((resolve, reject) => {
      this.csrService.getCurrentUser()
      .then(res => {

          return resolve(csr);

      }, err => {
        this.router.navigate(['/csr-angular']);
        return reject(err);
      });
    });
  }
}
