import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { ImageUploadFieldComponent } from './components/image-upload-field/image-upload-field.component';
import { FormWithImageInputComponent } from './components/form-with-image-input/form-with-image-input.component';
import { FormWithPasswordComponent } from './components/form-with-password/form-with-password.component';
import { PasswordFieldComponent } from './components/password-field/password-field.component';

@NgModule({
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
    exports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        ImageUploadFieldComponent,
        PasswordFieldComponent,
    ],
  declarations: [ImageUploadFieldComponent, FormWithImageInputComponent, FormWithPasswordComponent, PasswordFieldComponent],
})
export class SharedModule {}
