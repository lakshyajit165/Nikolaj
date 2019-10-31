import { Component, OnInit, Inject } from '@angular/core';
import { ICommand } from '../model/ICommand';
import { CommandService } from '../services/command.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

export interface DialogData {
  command: string;
}
@Component({
  selector: 'app-command-details-dialog',
  templateUrl: 'commanddetailsdialog.html'
})

export class CommandDetailsDialogComponent {

  private reportReason;


  constructor(
    public dialogRef: MatDialogRef<CommandDetailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
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
    public commandService: CommandService,
    public dialog: MatDialog) { }


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
  onSelectCommand(command: ICommand) {
    console.log(command);
    this.openDialog(command);
  }
  openDialog(command: ICommand): void {

    this.dialog.open(CommandDetailsDialogComponent, {
      width: '800px',
      data: {
        name: command.name,
        tags: command.tags,
        desc: command.desc,
        paramList: command.parameters,
        last_updated_on: command.last_updated_on,
        created_on: command.created_on,
        created_by: command.created_by,
        usage: command.usage,
        status: command.status
      }
    });
  }

}


