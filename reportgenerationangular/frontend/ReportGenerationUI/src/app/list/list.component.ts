import { Component, OnInit } from '@angular/core';
import { ReportService } from '../services/report.service';
import { IReport } from '../interfaces/IReport';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  reports: IReport[] = [];

  defaultValue: string = 'all';

  array = ['all', 'open', 'engaged', 'closed', 'bot' , 'callback'];

  page: number;

  limit: number;

  scrollStatus: boolean = true;

  status: string;

  count: number;

  noOfDocuments: number;

  totalNoOfDocuments: number = 0;

  pagination: boolean;

  // emptyData : boolean ;

  constructor(public reportService: ReportService) { }

  ngOnInit() {


    this.status = '';
    this.page = 0;
    this.limit = 10;
    this.reportService.getSize(this.status).subscribe(data => {
      this.count = data.result;
      this.noOfDocuments = data.result;
    })
    this.getAllReports(this.status);

  }

  getAllReports(value: string) {

    this.reportService.getReportsByStatus(value, this.page, this.limit).subscribe(data => {
      // this.emptyData = false;
      // if(data.result.length === 0)
      // {
      //   this.emptyData = true;
      //   console.log("inside if");
      // }
      if (this.count < 5) {
        this.scrollStatus = false;
      }
      if (data.result.length > 0) {
        this.pagination = true;
      }
      this.count = this.count - this.limit;
      this.totalNoOfDocuments = this.totalNoOfDocuments + data.result.length;
      this.reports.push(...data.result);              //array destructuring.....
    })

  }


  onScroll() {
    this.page = this.page + 1;
    this.getAllReports(this.status);
   
  }

  //when something is changes in select box
  selectionChange(value) {

    this.scrollStatus = true;
    this.status = value;
    this.page = 0;
    this.limit = 10;
    this.reports = [];
    this.count = 0;
    this.noOfDocuments = 0;
    this.totalNoOfDocuments = 0;
    this.pagination = false;

    if (this.status === 'all') {
      this.status = '';
      this.reportService.getSize(this.status).subscribe(data => {
        this.count = data.result;
        this.noOfDocuments = data.result;
      })
      this.getAllReports(this.status);
    }

    else {
      this.reportService.getSize(this.status).subscribe(data => {
        this.count = data.result;
        this.noOfDocuments = data.result;
      })
      this.getAllReports(this.status);
    }
  }



}
