import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import {Message} from '../model/Messageinterface';
import {Chats} from '../model/Chatsinterface';
import { ToastrService } from 'ngx-toastr';
import { SocketStorage } from '../model/socketstorageinterface';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {ChatsService} from '../services/chats.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { Ticket } from '../model/ticketinterface';
@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  private serverUrl = environment.url + 'socket';
  private uuId: string;
  isLoaded = false;
  loaded = false;
  isCustomSocketOpened = false;
  private stompClient;
  private ticket: Ticket;

  messages: Message[] = [];
  chats: Chats[] = [];
  title: string;
  description: string;
  usermail: string;
  csrmail: string;


  constructor(
    public http: HttpClient,
    private toastr: ToastrService,
    public chatservice: ChatsService,
    private cookie: CookieService,
    private router: Router
  ) {

    if (this.router.getCurrentNavigation().extras.state !== undefined) {
      this.ticket = this.router.getCurrentNavigation().extras.state.ticket;
      this.usermail = this.ticket.raisedBy;
    }



    this.chatservice.getChats(this.usermail).subscribe(data => {

      data.map(chatMessage => {
        const previousMessage: Message = { content: chatMessage.message,
          emailId: '', type: chatMessage.user, sender: '', hours: '', minutes: '' };
        this.messages.push(previousMessage);
        });

    });
  }

  ngOnInit() {

    this.csrmail = this.cookie.get('csrmail');
    // this.csrmail = "csr@gmail.com";
    this.uuId = this.uuid();
    this.initializeWebSocketConnection();

  }

  initializeWebSocketConnection() {
    const ws = new SockJS(this.serverUrl, '_reserved' , 10);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, (res) => {
    that.isLoaded = true;
    // this.openGlobalSocket();
    this.openSocket();
     // connected to user
    this.loaded = true;
    });
  }

  openSocket() {
    // if (this.isLoaded) {
      this.isCustomSocketOpened = true;
      this.stompClient.subscribe('/socket-publisher/' + this.uuId, (message) => {
        this.handleResult(message);

             });
      this.sendMessageWhenEstablished();
      // }
  }

  public uuid(): string {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (char) => {
      // tslint:disable-next-line: no-bitwise
      const random = Math.random() * 16 | 0; // Nachkommastellen abschneiden
      const value = char === 'x' ? random : (random % 4 + 8); // Bei x Random 0-15 (0-F), bei y Random 0-3 + 8 = 8-11 (8-b) gemäss RFC 4122
      return value.toString(16); // Hexadezimales Zeichen zurückgeben
    });
  }

  sendMessage(message: string) {

    const chatmessage: Message = { content: message, emailId: this.usermail, type: 'csr', sender: this.csrmail , hours: '', minutes: '' };
    this.stompClient.send('/socket-subscriber/send/message', {}, JSON.stringify(chatmessage));
    this.messages.push(chatmessage);
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
  const messageResult: Message = JSON.parse(message.body);
  this.messages.push(messageResult);
  this.toastr.success('new message recieved', null, {
    timeOut: 3000
  });
}
}

}
