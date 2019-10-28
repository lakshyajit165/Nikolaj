import { Component, OnInit, Input } from '@angular/core';
import { SuggestionService } from '../services/suggestion.service';
import { CommaExpr } from '@angular/compiler';
import { Suggestion } from '../model/suggestion';

@Component({
 selector: 'app-suggestion',
 templateUrl: './suggestion.component.html',
 styleUrls: ['./suggestion.component.css']
})

export class SuggestionComponent implements OnInit {

  @Input() ticketId: string;
  @Input() intent: string[];
 constructor(private suggestion: SuggestionService) { }
 response: Response;
 command: Suggestion;
 result = 'result';
 status: boolean;
 feedback: object;
  id: string;
  suggestions: string;

 public thumbUp = 'thumb_up';
 public thumbDown = 'thumb_down';
 ngOnInit() {
   this.id = this.ticketId;
   this.suggestion.getSuggestion(this.ticketId)
     .subscribe(data => {
       this.response = data;
       this.command = this.response[this.result];
       this.suggestions = this.command.suggestion;
       console.log(this.command);
       if (!(this.command == null)) {
         this.status = false;
       } else {
         this.status = true;
       }
     });
 }
public changeIconThumbUp(newIcon: string ) {
   this.thumbUp = newIcon ;
   this.thumbDown = '';
   this.suggestion.feedback(this.intent[0], this.command.suggestion, 5)
     .subscribe(data => {
     this.feedback = data;
     console.log(this.feedback);
   });
}
public changeIconThumbDown(newIcon: string) {
 this.thumbUp = '' ;
 this.thumbDown = newIcon;
 this.suggestion.feedback(this.intent[0], this.command.suggestion, 0)
   .subscribe(data => {
   this.feedback = data;
   console.log(this.feedback);
 });
}
}
