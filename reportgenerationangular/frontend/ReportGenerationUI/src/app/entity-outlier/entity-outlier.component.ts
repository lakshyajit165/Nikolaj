import { Component, OnInit, Input } from '@angular/core';
import { ReportService } from '../services/report.service';
import { ChartType, ChartOptions } from 'chart.js';
import { Label } from 'ng2-charts';
import { IService } from '../interfaces/IService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-entity-outlier',
  templateUrl: './entity-outlier.component.html',
  styleUrls: ['./entity-outlier.component.css']
})
export class EntityOutlierComponent implements OnInit {

  constructor(public reportService: ReportService, private router: Router) {}

  public serviceReport: IService[] = [];

  public dataSource: IService[] = [];

  public entity: string[] = new Array();

  public isBarChart: boolean;

  public queriesRaised: number[] = new Array();

  public queriesResolved: number[] = new Array();

  public leadTime: number[] = new Array();

  startDate: string;

  endDate: string;

  emptyData: boolean ;

  public scrollStatus = true;

  displayedColumns: string[] = ['entity', 'queriesRaised'];

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
        backgroundColor: [ '#B6BDF1', '#FFD5F6', '#37FACC', '#DAFCBB', '#FFFFC6', '#F77D97', '#4AA4F9'],
      },
    ];

  ngOnInit(): void {


    this.reportService.getServiceReport('', '').subscribe(data => {
      this.emptyData = false;
      if (data.result.length === 0) {
        this.emptyData = true;
      }
      this.serviceReport = data.result;
      console.log('service report ' + this.serviceReport);
      this.serviceReport.map(element => {
        this.entity.push(element.entity);
        this.queriesRaised.push(element.queriesRaised);
        this.queriesResolved.push(element.queriesResolved);
        this.leadTime.push(element.leadTime);
        this.dataSource = this.serviceReport;

      });
    });

  }


  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  // to convert the input from datepicker into the right format
  convert(date: Date): string {
    return date.toLocaleDateString('en-GB', {
      year: 'numeric', month: 'numeric', day: 'numeric'
    }).replace(/\//gi, '-').split('-').reverse().join('-');
  }

  // to empty the array of service report
  resetGraph() {
    this.entity.length = 0;
    this.queriesRaised.length = 0;
    this.queriesResolved.length = 0;
    this.leadTime.length = 0;
    this.dataSource.length = 0;
    this.dataSource.splice(0, this.dataSource.length);
    this.dataSource = [];
    console.log('kitni aarhi h ' + this.dataSource.length);
  }

  // when event is fired on selecting some particular date
  getStartDate(value: Date) {
    console.log(value);
    this.resetGraph();
    this.startDate = this.convert(value);
    this.sendDates();
  }


  getEndDate(value: Date) {
    this.resetGraph();
    this.endDate = this.convert(value);
    this.sendDates();

  }



  sendDates() {
    this.reportService.getServiceReport(this.startDate, this.endDate)
      .subscribe(data => {
        this.emptyData = false;
        if (data.result.length === 0) {
          this.emptyData = true;
        }
        this.serviceReport = data.result;
        this.serviceReport.map(element => {
          this.entity.push(element.entity);
          this.queriesRaised.push(element.queriesRaised);
          this.queriesResolved.push(element.queriesResolved);
          this.leadTime.push(element.leadTime);
          this.dataSource = this.serviceReport;
          console.log(data.result);
        });
      });
  }


  toggleChart() {
    this.isBarChart = !this.isBarChart;
    console.log('konsa chart->', this.isBarChart);

}

}
