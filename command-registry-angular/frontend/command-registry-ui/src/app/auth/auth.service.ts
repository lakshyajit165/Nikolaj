import { Injectable } from '@angular/core';

import { AngularFireAuth } from '@angular/fire/auth';
import * as firebase from 'firebase/app';

@Injectable()
export class AuthService {

  constructor(
   public afAuth: AngularFireAuth
 ) {}



  doLogin(email: string, password: string) {
    return new Promise<any>((resolve, reject) => {
      firebase.auth().signInWithEmailAndPassword(email, password)
      .then(res => {
        resolve(res);
      }, err => reject(err));
    });
  }

}

//   doLogout(){
//     return new Promise((resolve, reject) => {
//       if(firebase.auth().currentUser){
//         this.afAuth.auth.signOut();
//         resolve();
//       }else{
//         reject();
//       }
//     });
