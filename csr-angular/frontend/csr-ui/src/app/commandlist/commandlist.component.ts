import { Component, OnInit, Inject } from '@angular/core';
import { ICommand } from 'my-command-library/src/app/modules/command/model/CommandInterface';
import { CommandService } from '../services/command.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


// tslint:disable-next-line: component-class-suffix

@Component({
  selector: 'app-my-commands',
  templateUrl: './commandlist.component.html',
  styleUrls: ['./commandlist.component.css']
})
export class CommandlistComponent implements OnInit {

  private commands;
  private result = 'result';
  private error = '';
  constructor(
    private commandService: CommandService,
    public dialog: MatDialog
  ) { }



  ngOnInit() {
    this.commandService.getCommandList().subscribe(res => {
      // console.log(res[this.result]);
      // console.log(typeof res);
      this.commands = res[this.result];
      // console.log(this.commands);
    }, err => {
      this.error = 'Sorry! An error occured..';
      // console.log(err);
    }
    );
  }




}
