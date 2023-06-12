import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { ImageUploadFieldComponent } from './components/image-upload-field/image-upload-field.component';
import { FormWithImageInputComponent } from './components/form-with-image-input/form-with-image-input.component';
import { PasswordFieldComponent } from './components/password-field/password-field.component';
import { AddressFieldComponent } from './components/address-field/address-field.component';
import { TimePipe } from './pipes/time.pipe';
import { EnumerationPipe } from './pipes/enumeration.pipe';
import { ReverseEnumerationPipe } from './pipes/reverse-enumeration.pipe';
import { RestaurantItemCardComponent } from './components/restaurant-item-card/restaurant-item-card.component';
import { MapComponent } from './components/map/map.component';
import { NewOrderAttemptDialogComponent } from '../restaurants/components/restaurants/restaurant-profile/restaurant-items-container/new-order-attempt-dialog/new-order-attempt-dialog.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { ConfirmationDialogWithReasonInputComponent } from './components/confirmation-dialog-with-reason-input/confirmation-dialog-with-reason-input.component';
import { OrderCardComponent } from './components/order-card/order-card.component';
import { OrderCardContentComponent } from './components/order-card/order-card-content/order-card-content.component';
import { OrderCardFooterComponent } from './components/order-card/order-card-footer/order-card-footer.component';
import { ReportDialogComponent } from './components/order-card/report-dialog/report-dialog.component';
import { StarRatingModule } from 'angular-star-rating';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialModule,
    LeafletModule,
    StarRatingModule,
  ],
  exports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ImageUploadFieldComponent,
    PasswordFieldComponent,
    AddressFieldComponent,
    TimePipe,
    EnumerationPipe,
    ReverseEnumerationPipe,
    RestaurantItemCardComponent,
    MapComponent,
    OrderCardComponent,
  ],
  providers: [MapComponent],
  declarations: [
    ImageUploadFieldComponent,
    FormWithImageInputComponent,
    PasswordFieldComponent,
    AddressFieldComponent,
    TimePipe,
    EnumerationPipe,
    ReverseEnumerationPipe,
    RestaurantItemCardComponent,
    MapComponent,
    NewOrderAttemptDialogComponent,
    ConfirmationDialogComponent,
    ConfirmationDialogWithReasonInputComponent,
    OrderCardComponent,
    OrderCardContentComponent,
    OrderCardFooterComponent,
    ReportDialogComponent,
  ],
})
export class SharedModule {}
