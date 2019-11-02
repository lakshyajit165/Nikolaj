import { Component, OnInit } from '@angular/core';
import { PerformanceService } from '../services/performance.service';
import * as moment from 'moment';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.css']
})
export class PerformanceComponent implements OnInit {

  result = 'result';
  results = 'results';
  timestamp = 'timestamp';
  total = 'total';
  response1: object;
  response2: object;
  responseTaken: object[];
  responseResolved: object[];
  queryTakenCount: object[] = [];
  queryResolvedCount: object[] = [];
  dates: string[] = [];
  csrmail: string;
  loaded = false;

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

  public barChartLabels: Label[];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [pluginDataLabels];
  public barChartData: ChartDataSets[];


  constructor(
    private performanceService: PerformanceService,
    private cookie: CookieService
  ) {
    // console.log(this.dates);
    // console.log(this.queryResolvedCount);
    // console.log(this.queryTakenCount);
    // this.barChartLabels = this.dates;
    // this.barChartType = 'bar';
    // this.barChartData = [
    //     {data: this.queryTakenCount , label: 'Queries Taken'},
    //     {data : this.queryResolvedCount, label : 'Queries Resolved'}
    //   ];
  }

  ngOnInit() {

    this.csrmail = this.cookie.get('csrmail');
    this.getDetailsTaken();

  }

  public getDetailsTaken(): void {
    this.performanceService.getDetailsTaken(this.csrmail).subscribe(res => {
      this.response1 = res;
      this.responseTaken = this.response1[this.result];
      // console.log(this.responseTaken);

      this.dates = Object.keys(this.responseTaken);
      this.queryTakenCount = Object.values(this.responseTaken);

      // console.log(this.dates);
      // console.log(this.queryTakenCount);

      // this.responseTaken.forEach(ele => {
      //   // console.log('element is ', element);
      //   this.dates.push(moment(ele[this.timestamp]).format('DD-MM-YYYY'));
      //   this.queryTakenCount.push(ele[this.total]);
      // });


      // this.responseResolved = this.responseTaken.map(ele => {
      //   this.dates.push(moment(ele[this.timestamp]).format('DD-MM-YYYY'));
      //   return ele[this.total];
      // });
      this.performanceService.getDetailsResolved(this.csrmail).subscribe(response => {
        this.response2 = response;
        this.responseResolved = this.response2[this.result];

        this.queryResolvedCount = Object.values(this.responseResolved);

        // console.log(this.queryResolvedCount);

        this.barChartData = [
          {data: this.queryTakenCount , label: 'Queries Taken'},
          {data : this.queryResolvedCount, label : 'Queries Resolved'}
        ];

        this.barChartLabels = this.dates;

        this.loaded = true;

        // this.responseResolved.forEach(ele => {
        // this.queryResolvedCount.push(ele[this.total]);
      });

        // this.queryResolvedCount = this.responseResolved.map(ele => {
        //   return ele[this.total];
        // });

        // console.log(this.queryResolvedCount);
      });

      // console.log(this.dates);
      // console.log(this.queryTakenCount);

  }




}
