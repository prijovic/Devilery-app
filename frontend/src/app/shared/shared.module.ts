import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { ImageUploadFieldComponent } from './components/image-upload-field/image-upload-field.component';
import { FormWithImageInputComponent } from './components/form-with-image-input/form-with-image-input.component';
import { PasswordFieldComponent } from './components/password-field/password-field.component';
import { AddressFieldComponent } from './components/address-field/address-field.component';
import { TimePipe } from './pipes/time.pipe';
import {EnumerationPipe} from "./pipes/enumeration.pipe";

@NgModule({
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
  exports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ImageUploadFieldComponent,
    PasswordFieldComponent,
    AddressFieldComponent,
    TimePipe,
    EnumerationPipe
  ],
  declarations: [ImageUploadFieldComponent, FormWithImageInputComponent, PasswordFieldComponent, AddressFieldComponent, TimePipe, EnumerationPipe],
})
export class SharedModule {}
