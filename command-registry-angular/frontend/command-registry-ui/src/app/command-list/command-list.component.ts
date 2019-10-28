import { Component, OnInit, Inject } from '@angular/core';
import { ICommand } from '../model/ICommand';
import { CommandService } from '../services/command.service';

@Component({
  selector: 'app-command-list',
  templateUrl: './command-list.component.html',
  styleUrls: ['./command-list.component.css']
})
export class CommandListComponent implements OnInit {

  private commands: ICommand[];

  private errorMessage: string;
  // public ICommand = [];

  constructor(
    public commandService: CommandService ) { }


  ngOnInit() {
    this.commandService.getcommands()
      .subscribe(
        (data) => {
        this.commands = data.result;
        console.log(data.result);
      },
      error => this.errorMessage = error
      );

  }
}


