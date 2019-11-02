import { Component, OnInit, ViewChild } from '@angular/core';
import { TrackIssueService } from '../services/track-issue.service';
import { EventEmitter } from 'events';
import { DataSource } from '@angular/cdk/table';
import { FormGroup, FormControl } from '@angular/forms';
export interface Report {
  value: string;
  viewValue: string;
}
const event = new EventEmitter();
declare const load;
declare const sendData;
declare const sendDataCommand;
@Component({
  selector: 'app-track-issue',
  templateUrl: './track-issue.component.html',
  styleUrls: ['./track-issue.component.css']
})
export class TrackIssueComponent implements OnInit {
  selected = 'NoCommand-0';
  getData;
  getDataCommand;
  intentName;
  // @ViewChild('first', { read: ElementRef, static: false }) firstName: ElementRef<HTMLElement>;
  // firstNameAutofilled: boolean;

  command: string ;

  reports: Report[] = [
    { value: 'NoCommand-0', viewValue: 'No Command' },
    { value: 'NoIntent-1', viewValue: 'No Intent' }];
  scrollStatus: boolean;
  displayedColumns: string[] = ['name'];
  dataSource;
  dataSourceCommand;
  myIntent = new FormGroup({
    intentName: new FormControl('')
  });
  ngOnInit() {
    load(this.selected);
    // sendData();
    setInterval(() => {
      // console.log(sendData());
      if (sendData() !== undefined && this.getData !== sendData()) {
      this.getData = sendData();
      this.getDataCommand = sendDataCommand();
      console.log('recieved query', sendData());
      console.log('received intent', sendDataCommand());
      this.dataSource = sendData();
      this.dataSourceCommand = sendDataCommand();
    }
    }, 1000);
  }

  constructor(private trackIssueService: TrackIssueService) {
   }
   // tslint:disable-next-line:align

  // anotherMethod(value: string) {
  //   // console.log('Selected report', report );
  //    this.trackIssueService.getReportsByType(value).subscribe(data => {
  //     console.log(data.result);
  //    // this.value = data.result;
  //   });
  // }

  //     if (data.result.length === 0) {
  // getReports(value : string,page: number) {
  //   this.trackIssueService.getReportsByType(value,this.page).subscribe(data => {
  //       this.scrollStatus = false;
  //     }
  //     console.log(data.result);
  //     // this.reports.push(...data.result);
  //   });

  // }


  anotherMethod(report: Report) {
    document.getElementById('partitionSVG').firstElementChild.remove();
    // selectReport();
    load(this.selected);
    this.getData = sendData();
    console.log('data', this.getData);
  }

  getIntent() {
  console.log(this.command);
  load(this.selected);
  document.getElementById('partitionSVG').firstElementChild.remove();
  this.getDataCommand = sendDataCommand();
  console.log('data intent', this.getDataCommand.name);
  this.trackIssueService.mapIntentCommand(this.getDataCommand.name, this.command);
  console.log("intent command mapping done");
  }

}
