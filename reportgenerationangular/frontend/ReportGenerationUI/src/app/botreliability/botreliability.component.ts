import { Component, OnInit } from '@angular/core';
import { ReportService } from '../services/report.service';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { MatDatepickerInputEvent } from '@angular/material';
import { FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-performance',
  templateUrl: './botreliability.component.html'
})
export class BotRealiabilityComponent implements OnInit {

  constructor(private reportService: ReportService,
              private fb: FormBuilder) {
    this.barChartType = 'bar';
    this.barChartData = [
      { data: this.totalQuery, label: 'Total Queries' },
      { data: this.reopen, label: 'Reopened Tickets' },
      { data: this.avgRating, label: 'Average Rating' }

    ];
    this.barChartLabels = this.serviceName;
  }
  response1: object;
  result = 'result';
  results = 'results';
  responseQuery: object[];
  responseReopen: object[];
  responseAvgRating: object[];
  finalData: object[] = [];
  total = 'total';
  error = '';
  map = new Map<string, number[]>();
  serviceName: string[] = [];
  reopen: number[] = [];
  avgRating: number[] = [];
  totalQuery: number[] = [];
  maxDate = new Date();
  fromDate: Date;
  toDate: Date;
  public barChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      xAxes: [{}], yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  public barChartLabels: Label[];
  public barChartType: ChartType;
  public barChartLegend = true;
  public barChartPlugins = [pluginDataLabels];
  public barChartData: ChartDataSets[];
  event1: string;
  event2: string;

    ngOnInit() {
  }

  getStartDate(type: string, event: MatDatepickerInputEvent<Date>) {

    this.resetGraph();
    this.event1 = `${event.value}`;
  }


  getEndDate(type: string, event: MatDatepickerInputEvent<Date>) {

    this.resetGraph();
    this.event2 = `${event.value}`;
    this.addEvent();

  }
  resetGraph() {
    this.reopen.length = 0;
    this.avgRating.length = 0;
    this.totalQuery.length = 0;
    this.serviceName.length = 0;
  }

  addEvent() {
    this.resetGraph();
    this.reportService.getBotReliability(this.event1, this.event2).subscribe(
      res => {
        this.response1 = res;
        this.responseQuery = this.response1[this.result];
        this.responseQuery.forEach((data) => {
          data[this.results].forEach((element) => {
            if (this.map.has(element.service)) {
              const arr = this.map.get(element.service);
              arr.push(element.total);
              this.map.set(element.service, arr);
            } else {
              const key = element.service;
              const value = [];
              value.push(element.total);
              this.map.set(key, value);
            }
          });
        });
        for (const key of this.map.keys()) {
          this.serviceName.push(key);
        }
        this.barChartLabels = this.serviceName;
        for (const value of this.map.values()) {
          this.totalQuery.push(value[0]);
          this.avgRating.push(value[1]);
          this.reopen.push(value[2]);
        }
      }
    );
    this.map.clear();
  }

}
