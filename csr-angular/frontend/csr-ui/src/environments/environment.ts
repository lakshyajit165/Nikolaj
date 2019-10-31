// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  firebase: {
    apiKey: 'AIzaSyCaTPCDWetqOESc1QWqBWgdv72hIpStK6U',
    authDomain: 'angular-firebase-auth-19c56.firebaseapp.com',
    databaseURL: 'https://angular-firebase-auth-19c56.firebaseio.com',
    projectId: 'angular-firebase-auth-19c56',
    storageBucket: 'angular-firebase-auth-19c56.appspot.com',
    messagingSenderId: '648816135107'
  },
  url: "http://15.206.36.205:8765/csrservice/",
  url2: "http://15.206.36.205:8765/socketserver/",
  apigateway: 'http://15.206.36.205:8765/'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
