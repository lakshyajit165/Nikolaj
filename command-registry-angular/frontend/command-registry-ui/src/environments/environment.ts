// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  commandUrl: '/command-registry-angular/assets/data/commands.json',
  trackIssueUrl: '/command-registry-angular/assets/data/report.json',
  intentCommandMappingUrl: 'http://localhost:8082/api/v1/commandregistry/commands',
  firebase: {
    apiKey: 'AIzaSyDlV1Bi0vaUKMq-G5vUPHw1wwrGuw5kE9s',
    authDomain: 'command-registry-angular.firebaseapp.com',
    databaseURL: 'https://command-registry-angular.firebaseio.com',
    projectId: 'command-registry-angular',
    storageBucket: 'command-registry-angular.appspot.com',
    messagingSenderId: '330046441984',

  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
