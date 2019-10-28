import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


@Component({
  selector: 'app-userchat',
  templateUrl: './userchat.component.html',
  styleUrls: ['./userchat.component.css']
})
export class UserchatComponent implements OnInit {

  description: string;
  messages: string[];
  

  private serverUrl = 'http://localhost:8087/websocketApp';
  private stompClient;

  constructor(
    public http: HttpClient
  ) {
    this.initializeWebSocketConnection();
  }

  ngOnInit() {
    this.messages = [];
  }

  sendMessage(message: string) {
    //this.messages.push(message);
    let chatMessage = {
			sender : "User",
			content : message,
			type : 'CHAT'
		};
    this.stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(chatMessage));
    this.stompClient.send('/app/chat.botMessage', {}, JSON.stringify(chatMessage));
    (<HTMLInputElement>document.getElementById('chatmessage')).value='';
    
  }



  initializeWebSocketConnection() {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    // let that = this;
    this.stompClient.connect({}, (frame) => {
      //subscribe
      this.stompClient.subscribe('/topic/public', (message) => {
        if (message.body) {
         
          const payload = JSON.parse(message.body);
          // if(payload.sender === 'Optimus'){
            
            
          // }else{
          //   this.messageBackground = '#DFDFDF';
          // }
          this.messages.push(payload.sender + ': '+ payload.content);
          
      
        }
      });
      this.stompClient.send("/app/chat.welcomeFromBot", {}, JSON.stringify({
        sender : 'Optimus',
        type : 'initiate-chat'
    }));
    });
  }



}
