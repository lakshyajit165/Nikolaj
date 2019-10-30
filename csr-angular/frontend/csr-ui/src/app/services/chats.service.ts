import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Chats } from '../model/Chatsinterface'; 

@Injectable({
  providedIn: 'root'
})
export class ChatsService {

  chaturl: string = environment.url + "api/v1/socketserver/chats/";

  constructor(private httpClient: HttpClient) { 
  }

  getChats(emailId: string) {
    console.log(this.chaturl + emailId);
    return this.httpClient.get<Chats[]>(this.chaturl + emailId);
  }
  
}
