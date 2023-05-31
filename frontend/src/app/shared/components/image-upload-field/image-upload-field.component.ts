import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import { SafeUrl } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-image-upload-field',
  templateUrl: './image-upload-field.component.html',
  styleUrls: ['./image-upload-field.component.scss'],
})
export class ImageUploadFieldComponent {
  @Input() alterImage!: string;
  @Output() fileChanged = new EventEmitter<File>();
  image!: SafeUrl;

  constructor(@Inject(DOCUMENT) private document: Document) {}

  processFile($event: Event) {
    if ($event.target instanceof HTMLInputElement) {
      const files = $event.target.files;
      if (files && files.length > 0) {
        const file = files[0];
        if (this.document.defaultView) {
          this.image = this.document.defaultView.URL.createObjectURL(file);
          this.fileChanged.emit(file);
        }
      }
    }
  }

  getBackgroundImageUrl() {
    return `url("${this.image}")`;
  }
}
