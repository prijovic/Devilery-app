import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'enumeration'
})
export class EnumerationPipe implements PipeTransform {

  transform(value: string): string {
    if (!value) {
      return '';
    }

    return value.replace(/_/g, ' ').toLowerCase()
      .split(' ')
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  }

}
