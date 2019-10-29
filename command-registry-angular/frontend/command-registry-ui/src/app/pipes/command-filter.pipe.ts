import { Pipe, PipeTransform } from '@angular/core';
import { ICommand } from '../model/ICommand';

@Pipe({
  name: 'commandFilter'
})
export class CommandFilterPipe implements PipeTransform {

  transform(commands: ICommand[], searchTerm: string): ICommand[] {
    if (!commands || !searchTerm) {
      return commands;
    }
    return commands.filter(command =>
      command.name.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1);
  }

}
