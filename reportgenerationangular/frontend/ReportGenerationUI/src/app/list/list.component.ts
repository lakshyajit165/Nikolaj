import { Component, OnInit } from '@angular/core';
import { ReportService } from '../services/report.service';
import { IReport } from '../interfaces/IReport';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import {Message} from '../interfaces/Messageinterface';
import { SocketStorage } from '../interfaces/Socketstorageinterface';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Router } from '@angular/router';
import { HostBinding } from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';

@Component({
  selector: 'app-list',
  animations: [
    trigger('flyInOut', [
      state('in', style({ backgroundColor: 'none' })),
      transition(':enter', [
        style({ backgroundColor: 'blue' }),
        animate(1000)
      ]),
    ])
  ],
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})


export class ListComponent implements OnInit {

  reports: IReport[] = [];

  reverseReports: IReport[] = [];

  defaultValue = 'all';

  array = ['all', 'open', 'engaged', 'closed'];

  page: number;

  limit: number;

  scrollStatus = true;

  status: string;

  count: number;

  noOfDocuments: number;

  totalNoOfDocuments = 0;

  pagination: boolean;

  emptyData: boolean ;
  private serverUrl = environment.url + 'socket';
  private uuId: string;
  isLoaded = false;
  isCustomSocketOpened = false;
  private stompClient;

  messages: Message[] = [];
  title: string;
  description: string;
  usermail: string;
  csrmail: string;

  constructor(public reportService: ReportService,
              public http: HttpClient,
              private router: Router ) { }

  ngOnInit() {
    this.status = '';
    this.page = 0;
    this.limit = 10;
    this.reportService.getSize(this.status).subscribe(data => {
      this.count = data.result;
      if (this.count === 0) {
        this.emptyData = true;
        console.log('inside if');
      }
      this.noOfDocuments = data.result;
    });
    this.initializeWebSocketConnection();
    this.getAllReports(this.status);
  }

  getAllReports(value: string) {

    this.reportService.getReportsByStatus(value, this.page, this.limit).subscribe(data => {
      if (this.count < 10) {
        console.log('inside if count = ' + this.count);
        this.scrollStatus = false;
      }
      if (data.result.length > 0) {
        this.pagination = true;
      }
      this.count = this.count - this.limit;
      this.totalNoOfDocuments = this.totalNoOfDocuments + data.result.length;
      this.reports.push(...data.result);
      console.log("size of reports got = " + this.reports.length);
      // this.reverseReports = [];
      // this.reports.forEach(report => {
      //   this.reverseReports.unshift(report);
      //   // this.reverseReports.push(reeportsport);
      //   // this.reverseReports.sort((a, b) => new Date(b.createdOn).getTime() - new Date(a.createdOn).getTime());
      // });
      console.log(this.reports);
    });

  }


  onScroll() {
    this.page = this.page + 1;
    this.getAllReports(this.status);
    // this.emptyData = false;
  }

  // when something is changes in select box
  selectionChange(value) {

    this.scrollStatus = true;
    this.status = value;
    this.page = 0;
    this.limit = 10;
    this.reports = [];
    this.count = 0;
    this.noOfDocuments = 0;
    this.totalNoOfDocuments = 0;
    this.pagination = false;

    if (this.status === 'all') {
      this.status = '';
      this.reportService.getSize(this.status).subscribe(data => {
        this.count = data.result;
        // this.emptyData = false;
        // if (this.count === 0) {
        //   this.emptyData = true;
        //   console.log('inside if');
        // }
        this.noOfDocuments = data.result;
      });
      this.getAllReports(this.status);
    } else {
      this.reportService.getSize(this.status).subscribe(data => {
        this.count = data.result;
        this.noOfDocuments = data.result;
      });
      this.getAllReports(this.status);
    }
  }

initializeWebSocketConnection() {
  const ws = new SockJS(this.serverUrl, '_reserved' , 10);
  this.stompClient = Stomp.over(ws);
  const that = this;
  this.stompClient.connect({}, (res) => {
  that.isLoaded = true;
  this.openSocket();
  });
}

openSocket() {
    this.isCustomSocketOpened = true;
    this.stompClient.subscribe('/socket-publisher', (message) => {
      this.handleResult(message);
    });
    // this.sendMessageWhenEstablished();
    // }
}

sendMessage(message: string) {
  const chatmessage: Message = { content: message, emailId: this.usermail, type: 'csr', sender: this.csrmail };
  this.stompClient.send('/socket-subscriber/send/message', {}, JSON.stringify(chatmessage));
  // this.handleResult(chatmessage);
  ( document.getElementById('chatmessage') as HTMLInputElement).value = '';
}


sendMessageWhenEstablished() {
  const socketStorage: SocketStorage = { emailId: this.csrmail, socketId: this.uuId };
  // let message: Message = { content: this.uuId, emailId: 'this.userForm.value.fromId', type: this.userForm.value.toId, sender:'CHAT' };
  this.stompClient.send('/socket-subscriber/send/socketid', {}, JSON.stringify(socketStorage));
}

handleResult(message) {
  if (message.body) {

    // this.selectionChange(this.status);
    if (this.status === 'all') {
      this.status = '';
      this.totalNoOfDocuments = this.totalNoOfDocuments + 1;
      this.count = this.count + 1;
      this.noOfDocuments = this.noOfDocuments + 1;
    } else {
      this.reportService.getSize(this.status).subscribe(data => {
        this.totalNoOfDocuments = this.totalNoOfDocuments + 1;
        this.count = this.count + 1;
        this.noOfDocuments = this.noOfDocuments + 1;
      });
    }
    const reportData: IReport = JSON.parse(message.body);
    console.log("data result = " + reportData);
    this.reports.unshift(reportData);
    console.log("yahana aaarha h kya..........")
    // const messageResult: Message = JSON.parse(message.body);
    // this.messages.push(messageResult);
  }




}


}
