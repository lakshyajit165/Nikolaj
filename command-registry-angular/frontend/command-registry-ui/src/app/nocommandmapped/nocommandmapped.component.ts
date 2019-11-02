import { Component, OnInit } from '@angular/core';
import {AutofillMonitor} from '@angular/cdk/text-field';
import {AfterViewInit, ElementRef, OnDestroy, ViewChild} from '@angular/core';

@Component({
  selector: 'app-nocommandmapped',
  templateUrl: './nocommandmapped.component.html',
  styleUrls: ['./nocommandmapped.component.css']
})
export class NocommandmappedComponent implements OnInit {
  @ViewChild('first', { read: ElementRef, static: false }) firstName: ElementRef<HTMLElement>;
  firstNameAutofilled: boolean;

  // tslint:disable-next-line:variable-name
  constructor(private _autofill: AutofillMonitor) { }

  ngOnInit() {
    this._autofill.monitor(this.firstName)
        .subscribe(e => this.firstNameAutofilled = e.isAutofilled);
  }
  // tslint:disable-next-line:use-lifecycle-interface
  ngOnDestroy() {
    this._autofill.stopMonitoring(this.firstName);
  }

}
