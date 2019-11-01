import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { AngularFireAuth } from '@angular/fire/auth';
import * as firebase from 'firebase/app';

@Injectable()
export class DevService {

  constructor(
   public db: AngularFirestore,
   public afAuth: AngularFireAuth
 ) {
 }


  getCurrentUser() {
    return new Promise<any>((resolve, reject) => {

      // tslint:disable-next-line: no-shadowed-variable
      const dev = firebase.auth().onAuthStateChanged(dev =>  {
        if (dev) {
          resolve(dev);
        } else {
          reject('No user logged in');
        }
      });
    });
  }
}
