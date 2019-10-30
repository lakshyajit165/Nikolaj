import { Component, OnInit } from '@angular/core';
import { Icsr } from '../interfaces/Icsr';
import { ReportService } from '../services/report.service';
import { IPerformer } from '../interfaces/IPerformer';
import { MatTable } from '@angular/material';
import { ViewChild } from '@angular/core'

export interface Status {
  value: number;
  viewValue: string;
 }

@Component({
  selector: 'app-csrreliability',
  templateUrl: './csrreliability.component.html',
  styleUrls: ['./csrreliability.component.css']
})
export class CsrreliabilityComponent implements OnInit {

   @ViewChild('videoTable', {static: false}) videoTable: MatTable<Element>;
  public csrReport: Icsr[];
  response1: object;
  result = 'result';

  responseQuery: object[];
  reopen: number[] = [];
  totall: number[] = [];
  csrMail: string[] = [];
  efficiency: number[] = [];
  multipliedEfficiency: number[] = [];
  normalizedQueries: number[] = [];
  avgRating: number[] = [];
  succesRate: number[] = [];

  weightedEfficiency: number[] = [];
  weightedAvgRating: number[] = [];
  weightedSuccessRate: number[] = [];
  csrWeightedArray: number[] = [];
  rank: number[] = [];
  ans: number[] = [];
  sortable = [];
  hash: object = [];
  finalData: IPerformer[] = [];
  trialData: IPerformer[] = [];
  sortedData: IPerformer[] = [];
  dataSource: IPerformer[] = [];


    constructor(private reportService: ReportService) {
    }

      displayedColumns: string[] = ['Rank', 'Name', 'Performance'];

    ngOnInit() { }


  vehicleStatus: Status[] = [
    { value: 1, viewValue: 'January' },
    { value: 2, viewValue: 'Febuary' },
    {value: 3, viewValue: 'March'},
    {value: 4, viewValue: 'April'},
    {value: 5, viewValue: 'May'},
    {value: 6, viewValue: 'June'},
    {value: 7, viewValue: 'July'},
    {value: 8, viewValue: 'August'},
    {value: 9, viewValue: 'September'},
    {value: 10, viewValue: 'October'},
    {value: 11, viewValue: 'November'},
    {value: 12, viewValue: 'December'}
   ];

   Ranking: Status[] = [
    { value: 0, viewValue: 'All' },
    { value: 0.05, viewValue: 'Top 5%' },
    { value: 0.15, viewValue: 'Top 15%' },
    {value: 0.95, viewValue: 'Last 5%'},
    {value: 0.85, viewValue: 'Last 15%'}
   ]; 


    resetGraph() {
        this.avgRating.length = 0;
        this.efficiency.length = 0;
        this.normalizedQueries.length = 0;
        this.succesRate.length = 0;
    }

resetValues() {
  this.weightedAvgRating.length = 0;
  this.weightedEfficiency.length = 0;
  this.weightedSuccessRate.length = 0;
  this.csrWeightedArray.length = 0;
  this.dataSource.length = 0;
  this.csrMail.length = 0;
  this.rank.length = 0;
  this.ans.length = 0;
  console.log('csrmail ki length ki value' + this.csrMail.length);
}

  calculate() {
    this.efficiency.map(data => {
      this.weightedEfficiency.push(data / 10);
    });
    this.avgRating.map(data => {
      this.weightedAvgRating.push(data / 10);
    });
    this.succesRate.map(data => {
      this.weightedSuccessRate.push(data / 2.0);
    });
    }

    weightedMeanForCsr() {
      let ctr = 0;
      let efficiency;
      let normalizedQueries;
      while (ctr < this.weightedSuccessRate.length && ctr < this.weightedAvgRating.length &&
         ctr < this.weightedEfficiency.length && ctr < this.normalizedQueries.length) {
        efficiency = this.weightedEfficiency[ctr];
        normalizedQueries = this.normalizedQueries[ctr];
        this.ans.push(this.weightedSuccessRate[ctr] + this.weightedAvgRating[ctr] +
           parseFloat(efficiency) + parseFloat(normalizedQueries));
        ctr++;
     }
    }

    performerListMethod() {
      let x = 0;
      while (x <= this.weightedSuccessRate.length) {
        this.hash[`${this.csrMail[x]}`] = this.ans[x];
        x++;
      }
   }

   sortPerformerList() {
    for (const value of Object.keys(this.hash)) {
     for (const number in this.hash) {
        this.sortable.push([number, this.hash[number]]);
     }
     this.sortable.sort(function(a, b) {
       return b[1] - a[1];
     });
    }
   }


   rankGenerator() {
    let i = 1;
    const len = this.csrMail.length;
    for (i; i <= len; i++) {
      this.rank.push(i);
    }
  }

  generateData() {
    let x = 0;
    this.finalData.length = 0;
    while (x < this.rank.length) {
        this.finalData.push({
          rank: this.rank[x],
          name: this.csrMail[x],
          performance: this.ans[x]
        });
        x++;
      }
    return this.finalData;
  }

  onChange(newValue) {
    this.resetGraph();
    this.resetValues();
    this.reportService.getCsrRelaibility(newValue).subscribe(
      res => {

        this.response1 = res;
        this.csrReport = this.response1[this.result];
        for (const value of Object.values(this.csrReport)) {
          for (const key of Object.keys(value)) {
              this.csrMail.push(key);
              this.succesRate.push((value[key].successRate).toFixed(6));
              this.efficiency.push((value[key].efficiency).toFixed(6));
              this.normalizedQueries.push((value[key].normalizedQueriesTaken));
              this.avgRating.push((value[key].avgRating).toFixed(2));
          }
      }
        this.calculate();
        this.weightedMeanForCsr();
        this.performerListMethod();
        this.sortPerformerList();
        this.rankGenerator();
      });
      }

      onChangeRanking(newValue) {
        const x = this.rank.length * newValue;
        this.dataSource.length = 0;
        this.dataSource.splice(0, this.trialData.length);
        this.dataSource = [];

        if (newValue === 0 ) {
          this.trialData.length = 0 ;
          this.generateData().filter(data => {
            this.trialData.push(data);
            this.dataSource.push(...this.trialData);
            this.videoTable.renderRows();
          });
        }else{
          this.trialData = [];
          this.dataSource.push(...this.trialData);
          this.videoTable.renderRows();
        }

        if (newValue < 0.2 && newValue > 0) {
          this.trialData.length = 0 ;
          this.generateData().filter(data => {
            if (data.rank < x) {
              this.trialData.push(data);
              this.dataSource.push(...this.trialData);
              this.videoTable.renderRows();
            }
            else{
              this.trialData = [];
              this.dataSource.push(...this.trialData);
              this.videoTable.renderRows();
            }
          });
        }
        
        
        if (newValue > 0.8) {
          this.trialData.length = 0 ;
          this.generateData().filter(data => {
            if (data.rank > x) {
              this.trialData.push(data);
              this.dataSource.push(...this.trialData);
              this.videoTable.renderRows();
            }
            else{
              this.trialData = [];
              this.dataSource.push(...this.trialData);
              this.videoTable.renderRows();
            }
          });
        }

        this.dataSource = this.trialData;
       }

       getRecord(Name) {
         console.log(Name);
       }

}
