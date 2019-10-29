import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'issueFilter'
})
export class IssueFilterPipe implements PipeTransform {

  transform(issues: any[], selectedIssueType: string): any[] {
    if (!issues || !selectedIssueType) {
      return issues;
    }
    return issues.filter(issue =>
      issue.type.toLowerCase().indexOf(selectedIssueType.toLowerCase()) !== -1);
  }

}
