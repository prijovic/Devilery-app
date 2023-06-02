import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'reverseEnumeration'
})
export class ReverseEnumerationPipe implements PipeTransform {

  transform(value: string): string {
    if (!value) {
      return '';
    }

    return value.toUpperCase()
      .split(' ')
      .join('_');
  }

}
