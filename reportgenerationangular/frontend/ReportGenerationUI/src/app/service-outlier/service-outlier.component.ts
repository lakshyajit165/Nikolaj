import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { IService } from '../interfaces/IService';
import { ReportService } from '../services/report.service';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { Router } from '@angular/router';


@Component({
  selector: 'app-service-outlier',
  templateUrl: './service-outlier.component.html',
  styleUrls: ['./service-outlier.component.css']
})
export class ServiceOutlierComponent implements OnInit {

  public serviceReport: IService[] = [];

  public entity: string[] = new Array();

  public queriesRaised: number[] = new Array();

  public queriesResolved: number[] = new Array();

  public leadTime: number[] = new Array();

  event1: string;
  event2: string;

  public barChartOptions: ChartOptions = {
    legend: {
      position: 'bottom',
    },
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  public barChartLabels: Label[] = this.entity;
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [pluginDataLabels];

  public barChartData: ChartDataSets[] = [
    { data: this.queriesRaised, label: 'Queries Raised' },
    { data: this.queriesResolved, label: 'Queries Resolved' },
    { data: this.leadTime, label: 'Lead Time' },
  ];


  constructor(public reportService: ReportService, private router: Router) {
  }

  ngOnInit() {

    this.reportService.getServiceReport('', '').subscribe(data => {
      this.serviceReport = data.result;
      console.log("service report " + this.serviceReport);
      this.serviceReport.map(element => {
        this.entity.push(element.entity);
        this.queriesRaised.push(element.queriesRaised);
        this.queriesResolved.push(element.queriesResolved);
        this.leadTime.push(element.leadTime);

      });
    })
  }

   //to convert the input from datepicker into the right format
   convert(str) {
    var date = new Date(str),
      mnth = ("0" + (date.getMonth() + 1)).slice(-2),
      day = ("0" + date.getDate()).slice(-2);
    return [date.getFullYear(), mnth, day].join("-");
  }

  //to empty the array of service report
  resetGraph() {
    this.entity.length = 0;
    this.queriesRaised.length = 0;
    this.queriesResolved.length = 0;
    this.leadTime.length = 0;
  }

  //when event is fired on selecting some particular date

  getStartDate(type: string, event: MatDatepickerInputEvent<Date>) {

    this.resetGraph();
    this.event1 = `${event.value}`;
  }


  getEndDate(type: string, event: MatDatepickerInputEvent<Date>) {

    this.resetGraph();
    this.event2 = `${event.value}`;
    this.sendDates();

  }



  sendDates() {
    this.reportService.getServiceReport(this.convert(this.event1), this.convert(this.event2))
      .subscribe(data => {
        this.serviceReport = data.result;
        this.serviceReport.map(element => {
          this.entity.push(element.entity);
          this.queriesRaised.push(element.queriesRaised);
          this.queriesResolved.push(element.queriesResolved);
          this.leadTime.push(element.leadTime);
          console.log(data.result)
        });
      });
  }


  yourfunc(e) {
    
    console.log("aaraha h kya");
    this.router.navigate(['/servicereport']);       
  
}

}
