import { Component, OnInit } from '@angular/core';
import { ReportService } from '../services/report.service';
import { ChartDataSets, ChartType, ChartOptions } from 'chart.js';
import { Label } from 'ng2-charts';
import { IService } from '../interfaces/IService';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { Color } from 'ng2-charts';
import { Router } from '@angular/router';


@Component({
  selector: 'app-service-report',
  templateUrl: './service-report.component.html',
  styleUrls: ['./service-report.component.css']
})
export class ServiceReportComponent implements OnInit {

  public serviceReport: IService[] = [];
  
  public dataSource: IService[] = [];

  public entity: string[] = new Array();

  public queriesRaised: number[] = new Array();

  public queriesResolved: number[] = new Array();

  public leadTime: number[] = new Array();

  event1: string;
  event2: string;

  displayedColumns: string[] = ['entity', 'queriesRaised'];
  
  ngOnInit() {

    this.reportService.getServiceReport('', '').subscribe(data => {
      this.serviceReport = data.result;
      console.log("service report " + this.serviceReport);
      this.serviceReport.map(element => {
        this.entity.push(element.entity);
        this.queriesRaised.push(element.queriesRaised);
        this.queriesResolved.push(element.queriesResolved);
        this.leadTime.push(element.leadTime);
        this.dataSource = this.serviceReport;

      });
    })
  }


  constructor(public reportService: ReportService, private router: Router) {
  }

  public pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      position: 'bottom',
    },
    plugins: {
      datalabels: {
        formatter: (value, ctx) => {
          const label = ctx.chart.data.labels[ctx.dataIndex];
          return label;
        },
      },
    }
  };
  public pieChartLabels: Label[] = this.entity;
  public pieChartData: number[] = this.queriesRaised;
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartColors = [
    {
      backgroundColor: [ "#B6BDF1", "#FFD5F6", "#37FACC", "#DAFCBB", "#FFFFC6", "#F77D97", "#4AA4F9"],
    },
  ];


  public lineChartData: ChartDataSets[] = [
    { data: this.queriesRaised, label: 'Queries Raised (No)', fill: false },
    { data: this.queriesResolved, label: 'Queries Resolved (No)', fill: false },
    { data: this.leadTime, label: 'Average Lead Time (Hr)', yAxisID: 'y-axis-1', fill: false }
  ];

  public lineChartLabels: Label[] = this.entity;

  public lineChartOptions = {

    responsive: true,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{}],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'left',
        },
        {
          id: 'y-axis-1',
          position: 'right',
          gridLines: {
            color: 'rgba(255,0,0,0.3)',
          },
          ticks: {
            fontColor: 'black',
          }
        }
      ]
    },
    legend: {
      position: 'bottom',
      display: true,
      labels: {
        fontColor: 'rgb(255, 99, 132)'
      }
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'vertical',
          scaleID: 'x-axis-0',
          value: 'March',
          borderColor: 'orange',
          borderWidth: 2,
          label: {
            enabled: true,
            fontColor: 'orange',
            content: 'LineAnno',
            position: 'bottom'
          }
        },
      ],
    },
  };

  public lineChartColors: Color[] = [

    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // red
      backgroundColor: 'rgba(22,120,206,0.3)',
      borderColor: 'blue',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // red
      backgroundColor: 'rgba(22,120,206,0.3)',
      borderColor: 'blue',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];

  public lineChartLegend = true;
  public lineChartType = 'line';

  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
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
    this.dataSource.length=0;
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
          this.dataSource=this.serviceReport;
          console.log(data.result)
        });
      });
  }

  yourfunc(e) {
    
      console.log("aaraha h kya");
      this.router.navigate(['/serviceoutlier']);    
      
      //move to bar chart that is service outlier report
    
 }

}