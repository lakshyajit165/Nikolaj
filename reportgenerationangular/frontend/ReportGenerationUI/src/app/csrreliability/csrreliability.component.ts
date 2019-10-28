import { Component, OnInit } from '@angular/core';
import { Icsr } from "../interfaces/Icsr";
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label, Color } from 'ng2-charts';
import { MatDatepickerInputEvent } from '@angular/material';
import { ReportService } from '../services/report.service';

@Component({
  selector: 'app-csrreliability',
  templateUrl: './csrreliability.component.html',
  styleUrls: ['./csrreliability.component.css']
})
export class CsrreliabilityComponent implements OnInit {

  event1: string;
    event2: string;
    response1: object;
    result = 'result';
    public csrReport: Icsr[];

    responseQuery: object[];
    reopen: number[] = [];
    totall: number[] = [];
    csrMail: string[] = [];
    efficiency: number[] = [];
    multipliedEfficiency: number[] = [];
    normalizedQueries: number[] = [];
    avgRating: number[] = [];
    succesRate: number[] = [];

    maxDate = new Date();
    minDate = new Date(2000, 0, 1);

    public barChartLabels: Label[];
    public barChartType: ChartType;
    public barChartLegend = true;
    public barChartPlugins = [pluginDataLabels];
    public barChartData: ChartDataSets[];

    public barChartOptions: ChartOptions = {
        responsive: true,
        scales: { xAxes: [{}], yAxes: [{
         ticks: {
           beginAtZero: true
       }
        }] },
        plugins: {
          datalabels: {
            anchor: 'end',
            align: 'end',
          }
        }
      };

      public barChartColors: Color[] = [
        { backgroundColor: '#d1f4a7' },
        { backgroundColor: '#ccd7f6' },
        { backgroundColor: '#f9ce80' },
        { backgroundColor: '#dab0a4' }
      ];

    constructor(private reportService: ReportService) {
        this.barChartType = 'bar';

        this.barChartData = [
        { data : this.efficiency , label: 'Efficiency'},
        { data : this.avgRating , label: 'Avg Rating'},
        { data : this.succesRate , label: 'Success Rate'},
        { data : this.normalizedQueries , label: 'Took Queries in compare to other'}
      ];
        this.barChartLabels = this.csrMail;
    }

    ngOnInit() { }
    resetGraph() {
        this.avgRating.length = 0;
        this.efficiency.length = 0;
        this.normalizedQueries.length = 0;
        this.succesRate.length = 0;
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

    addEvent() {
        this.reportService.getCsrRelaibility(this.event1, this.event2).subscribe(
          res => {
              this.response1 = res;
              console.log(res);
              this.csrReport = this.response1[this.result];
              console.log(this.csrReport);
              for (const value of Object.values(this.csrReport)) {
                console.log(value);
                for (const key of Object.keys(value)) {
                    console.log(key);
                    this.csrMail.push(key);
                    console.log(this.csrMail);
                    console.log(typeof value[key].successRate);
                    this.succesRate.push((value[key].successRate).toFixed(6));
                    console.log(this.succesRate);
                    this.efficiency.push((value[key].efficiency).toFixed(6));
                    console.log(this.efficiency);
                    this.normalizedQueries.push((value[key].normalizedQueriesTaken).toFixed(6));
                    console.log(this.normalizedQueries);
                    this.avgRating.push((value[key].avgRating).toFixed(2));
                    console.log(this.avgRating);
                }
            }
        });
    }

}
