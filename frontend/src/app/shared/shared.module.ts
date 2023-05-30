import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { ImageUploadFieldComponent } from './components/image-upload-field/image-upload-field.component';

@NgModule({
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
  exports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ImageUploadFieldComponent,
  ],
  declarations: [ImageUploadFieldComponent],
})
export class SharedModule {}
