import { Component, Inject } from '@angular/core';
import { DIALOG_DATA, DialogRef } from '@angular/cdk/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { textValidator } from '../../validators/text.validator';

@Component({
  selector: 'app-confirmation-dialog-with-reason-input',
  templateUrl: './confirmation-dialog-with-reason-input.component.html',
  styleUrls: ['./confirmation-dialog-with-reason-input.component.scss'],
})
export class ConfirmationDialogWithReasonInputComponent {
  formGroup: FormGroup<{ reason: FormControl }> = new FormGroup({
    reason: new FormControl(null, [Validators.required, textValidator]),
  });

  constructor(
    public dialogRef: DialogRef<string>,
    @Inject(DIALOG_DATA)
    public data: {
      title: string;
      text: string;
      confirmationButtonText: string;
      cancelButtonText: string;
    }
  ) {}

  confirm() {
    if (this.formGroup.valid) {
      const reason = this.formGroup.controls.reason.value;
      this.dialogRef.close(reason);
    }
  }
}
