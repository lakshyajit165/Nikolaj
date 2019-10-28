import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'intentFilter'
})
export class IntentFilterPipe implements PipeTransform {

  transform(intents: any[], searchIntent: string): any[] {
    if (!intents || !searchIntent) {
      return intents;
    }
    return intents.filter(intent =>
      intent.name.toLowerCase().indexOf(searchIntent.toLowerCase()) !== -1);
  }

}
