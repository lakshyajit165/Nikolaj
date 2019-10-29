import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { AngularFireAuth } from '@angular/fire/auth';
import * as firebase from 'firebase/app';

@Injectable()
export class CsrService {

  constructor(
   public db: AngularFirestore,
   public afAuth: AngularFireAuth
 ) {
 }


  getCurrentUser() {
    return new Promise<any>((resolve, reject) => {

      // tslint:disable-next-line: no-shadowed-variable
      const csr = firebase.auth().onAuthStateChanged(csr =>  {
        if (csr) {
          resolve(csr);
        } else {
          reject('No user logged in');
        }
      });
    });
  }

}
