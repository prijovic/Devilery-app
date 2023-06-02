import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timePipe'
})
export class TimePipe implements PipeTransform {

  transform(value: string | undefined | null): string {
    if (!value) {
      return '';
    }
    const parts = value.split(":");
    return parts[0] + ":" + parts[1];
  }

}
