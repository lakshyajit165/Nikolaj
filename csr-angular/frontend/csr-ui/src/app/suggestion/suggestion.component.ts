import { Component, OnInit, Input } from '@angular/core';
import { SuggestionService } from '../services/suggestion.service';
import { CommaExpr } from '@angular/compiler';
import { Suggestion } from '../model/suggestion';
import { FormBuilder } from '@angular/forms';
import { NoIntent } from '../model/nointent';
import { Feedback } from '../model/feedback';
@Component({
selector: 'app-suggestion',
templateUrl: './suggestion.component.html',
styleUrls: ['./suggestion.component.css']
})
export class SuggestionComponent implements OnInit {


constructor(private suggestion: SuggestionService, private form:FormBuilder) { }
 @Input() ticketId: string;
 @Input() intents: string[];
response: Response;
suggestedCommand: Suggestion;
result = 'result';
status: boolean;
feedback: object;

feedBack: Feedback = {
  intentName: '',
  commandName: '',
  rating: 0
};

 id: string;
 suggestions: string;
public thumbUp = 'thumb_up';
public thumbDown = 'thumb_down';
reportForm = this.form.group({
 entity: [''],
 intent: [''],
 command: [''],
 ticketId: this.ticketId
});
ngOnInit() {
  this.id = this.ticketId;
  this.suggestion.getSuggestion(this.ticketId)
    .subscribe(data => {
      this.response = data;
      this.suggestedCommand = this.response[this.result];
      this.suggestions = this.suggestedCommand.suggestion;
      console.log(this.suggestedCommand);
      if (!(this.suggestedCommand == null)) {
        this.status = true;
      } else {
        this.status = false;
      }
    });

}


public changeIconThumbUp(newIcon: string ) {
  this.thumbUp = newIcon ;
  this.thumbDown = '';
  console.log('inside thnumbs up');
  console.log(this.intents[0]);
  console.log( this.suggestedCommand.suggestion);
  this.feedBack.intentName = this.intents[0];
  this.feedBack.commandName = this.suggestedCommand.suggestion;
  this.feedBack.rating = 5;
  this.suggestion.feedback(this.feedBack)
    .subscribe(data => {
    this.feedback = data;
    console.log(this.feedback);
  });
}


public changeIconThumbDown(newIcon: string) {
this.thumbUp = '' ;
this.thumbDown = newIcon;
console.log('inside thnumbs down');
console.log(this.intents[0]);
console.log( this.suggestedCommand.suggestion);
this.feedBack.intentName = this.intents[0];
this.feedBack.commandName = this.suggestedCommand.suggestion;
this.feedBack.rating = 0;
this.suggestion.feedback(this.feedBack)
  .subscribe(data => {
  this.feedback = data;
  console.log(this.feedback);
});
}


public onSubmit(updatedReportDetails) {
  this.intents = ['defined'];
updatedReportDetails.id=this.ticketId;
this.suggestion.nointent(updatedReportDetails)
.subscribe(data =>{
 this.feedback=data;
});
}
}
