import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-password-field',
  templateUrl: './password-field.component.html',
  styleUrls: ['./password-field.component.scss']
})
export class PasswordFieldComponent implements OnInit {
  @Output() passwordChanged: EventEmitter<string> = new EventEmitter<string>();
  hidePassword = true;
  passwordGroup: FormGroup<{password: FormControl}> = new FormGroup({
    password: new FormControl('', [Validators.required])
  }
  );

  ngOnInit() {
    this.passwordGroup.controls.password.valueChanges.subscribe(value => this.passwordChanged.emit(value));
  }

  toggleShowPassword(event: Event) {
    event.preventDefault();
    this.hidePassword = !this.hidePassword;
  }
}
