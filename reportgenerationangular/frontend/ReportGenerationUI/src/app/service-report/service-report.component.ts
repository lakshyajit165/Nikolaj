import { Component, OnInit, Input} from '@angular/core';
import { ReportService } from '../services/report.service';
import { ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';
import { IService } from '../interfaces/IService';
import { Color } from 'ng2-charts';
import { Router } from '@angular/router';


@Component({
  selector: 'app-service-report',
  templateUrl: './service-report.component.html',
  styleUrls: ['./service-report.component.css']
})
export class ServiceReportComponent implements OnInit {


  constructor(public reportService: ReportService, private router: Router) {
  }


   public dataSource: IService[] = [];

   public serviceReport: IService[] = [];

   public entity: string[] = new Array();

   public queriesRaised: number[] = new Array();

   public queriesResolved: number[] = new Array();

   public leadTime: number[] = new Array();

   @Input() startDate: string;
   @Input() endDate: string;

  public scrollStatus = true;

  displayedColumns: string[] = ['entity', 'queriesRaised'];


  // tslint:disable-next-line: member-ordering
  public lineChartData: ChartDataSets[] = [
    { data: this.queriesRaised, label: 'Queries Raised (No)', fill: false },
    { data: this.queriesResolved, label: 'Queries Resolved (No)', fill: false },
    { data: this.leadTime, label: 'Average Lead Time (Hr)', yAxisID: 'y-axis-1', fill: false }
  ];

  // tslint:disable-next-line: member-ordering
  public lineChartLabels: Label[] = this.entity;

  // tslint:disable-next-line: member-ordering
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

  // tslint:disable-next-line: member-ordering
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

  // tslint:disable-next-line: member-ordering
  public lineChartLegend = true;
  public lineChartType = 'line';

  ngOnInit() {
  }


  sendDates() {

    this.reportService.getServiceReport(this.startDate, this.endDate)
      .subscribe(data => {
        this.serviceReport = data.result;
        this.serviceReport.map(element => {
          this.entity.push(element.entity);
          this.queriesRaised.push(element.queriesRaised);
          this.queriesResolved.push(element.queriesResolved);
          this.leadTime.push(element.leadTime);
          this.dataSource = this.serviceReport;
        });
      });
  }

  // tslint:disable-next-line: use-lifecycle-interface
  ngOnChanges() {
    this.resetGraph();
    this.sendDates();
  }

    // to empty the array of service report
    resetGraph() {
      this.entity.length = 0;
      this.queriesRaised.length = 0;
      this.queriesResolved.length = 0;
      this.leadTime.length = 0;
    }

}
