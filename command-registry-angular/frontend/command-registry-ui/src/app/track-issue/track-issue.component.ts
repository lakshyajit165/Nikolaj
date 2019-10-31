import { Component, OnInit } from '@angular/core';
import { TrackIssueService } from '../services/track-issue.service';
import { EventEmitter } from 'events';
import { DataSource } from '@angular/cdk/table';
export interface Report {
  value: string;
  viewValue: string;
}
const event = new EventEmitter();
declare const load;
declare const sendData;
@Component({
  selector: 'app-track-issue',
  templateUrl: './track-issue.component.html',
  styleUrls: ['./track-issue.component.css']
})
export class TrackIssueComponent implements OnInit {
  selected = 'NoCommand-0';
  getData;


  reports: Report[] = [
    { value: 'NoCommand-0', viewValue: 'No Command' },
    { value: 'NoIntent-1', viewValue: 'No Intent' }];
  scrollStatus: boolean;
  displayedColumns: string[] = ['name'];
  dataSource;
  ngOnInit() {
    load(this.selected);
    // sendData();
    setInterval(() => {
      // console.log(sendData());
      if (sendData() !== undefined && this.getData !== sendData()) {
      this.getData = sendData();
      console.log('recieved', sendData());
      this.dataSource = sendData();
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

  // getReports(value : string,page: number) {
  //   this.trackIssueService.getReportsByType(value,this.page).subscribe(data => {
  //     if (data.result.length === 0) {
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

}
