import { Pipe, PipeTransform } from '@angular/core';
@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {
  transform(items: any[], searchText: string): any[] {
    if (!items) { return []; }
    if (!searchText) { return items; }
    searchText = searchText.toLowerCase();
    return items.filter( it => {
  // console.log(it);
  // console.log(it.tags);
  return (it.tags.filter(ele => ele.toLowerCase().includes(searchText)).length > 0 || (it.description).toLowerCase().includes(searchText));
    });
   }
}


// @Pipe({
//   name: 'filter'
// })
// export class FilterPipe implements PipeTransform {
//   transform(items: any[], searchText: string): any[] {
//     if(!items) return [];
//     if(!searchText) return items;
// searchText = searchText.toLowerCase();
// return items.filter( it => {
//   console.log(it);
//   console.log(it.tags);
//       return (it.tags.foreach(ele=>ele.toLowerCase().includes(searchText)));
//     });
//    }
// }
