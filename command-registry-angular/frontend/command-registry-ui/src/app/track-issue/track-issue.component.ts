import { Component, OnInit, ɵɵpureFunction1 } from '@angular/core';
import { TrackIssueService } from '../services/track-issue.service';

export interface Report {
  value: string;
  viewValue: string;
}

declare const load;

@Component({
  selector: 'app-track-issue',
  templateUrl: './track-issue.component.html',
  styleUrls: ['./track-issue.component.css']
})
export class TrackIssueComponent implements OnInit {
   selected = 'NoCommand-0';
   toggledClass = 'show';


  reports: Report[] = [
    {value: 'NoCommand-0', viewValue: 'No Command'},
    {value: 'NoIntent-1', viewValue: 'No Intent'} ];
  scrollStatus: boolean;
  ngOnInit() {
    load(this.selected);
    // this.status = '';
    // this.page = 0;
    // //this.limit = 10;
    // this.getReports(this.status);
  }
  constructor(private trackIssueService: TrackIssueService) { }
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
  console.log('Selected report', this.selected);
  load(this.selected);

}
}
