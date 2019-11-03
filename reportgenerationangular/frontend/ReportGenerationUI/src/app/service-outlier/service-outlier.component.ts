import { Component, OnInit , Input, OnChanges } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { IService } from '../interfaces/IService';
import { ReportService } from '../services/report.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-service-outlier',
  templateUrl: './service-outlier.component.html',
  styleUrls: ['./service-outlier.component.css']
})
export class ServiceOutlierComponent implements OnInit, OnChanges {

 public serviceReport: IService[] = [];

 public entity: string[] = new Array();
 public queriesRaised: number[] = new Array();

 public queriesResolved: number[] = new Array();

 public leadTime: number[] = new Array();

 @Input() startDate: string;

 @Input() endDate: string;

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
    { data: this.queriesRaised, label: 'Queries Raised (No.)' },
    { data: this.queriesResolved, label: 'Queries Resolved (No.)' },
    { data: this.leadTime, label: 'Lead Time (Hrs)' },
  ];


  constructor(public reportService: ReportService, private router: Router) {
  }

  ngOnInit() {

  }

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


  sendDates() {

    this.reportService.getServiceReport(this.startDate, this.endDate)
      .subscribe(data => {
        this.serviceReport = data.result;
        this.serviceReport.map(element => {
          this.entity.push(element.entity);
          this.queriesRaised.push(element.queriesRaised);
          this.queriesResolved.push(element.queriesResolved);
          this.leadTime.push(element.leadTime);
        });
      });
  }



}
