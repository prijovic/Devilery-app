import { Component } from '@angular/core';

@Component({
  selector: 'app-form-with-image-input',
  templateUrl: './form-with-image-input.component.html',
  styleUrls: ['./form-with-image-input.component.scss']
})
export class FormWithImageInputComponent {
  picture: File | undefined;

  imageChanged($event: File) {
    this.picture = $event;
  }
}
