import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { DevService } from './dev.service';
import { FirebaseDevModel } from './dev.model';

@Injectable()
export class DevResolver implements Resolve<FirebaseDevModel> {

  constructor(public devService: DevService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot): Promise<FirebaseDevModel> {

    const csr = new FirebaseDevModel();

    return new Promise((resolve, reject) => {
      this.devService.getCurrentUser()
      .then(res => {

          return resolve(csr);

      }, err => {
        this.router.navigate(['/command-registry-angular']);
        return reject(err);
      });
    });
  }
}
