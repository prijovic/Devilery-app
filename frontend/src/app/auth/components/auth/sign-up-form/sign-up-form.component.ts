import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-up-form',
  templateUrl: './sign-up-form.component.html',
  styleUrls: ['./sign-up-form.component.scss'],
})
export class SignUpFormComponent implements OnInit {
  hidePassword = true;
  profilePicture: File | undefined;
  signUpForm!: FormGroup;

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      surname: new FormControl(null, [Validators.required]),
      phoneNumber: new FormControl(null, [Validators.required]),
    });
  }

  imageChanged($event: File) {
    this.profilePicture = $event;
  }

  toggleShowPassword(event: Event) {
    event.preventDefault();
    this.hidePassword = !this.hidePassword;
  }

  signUp() {
    if (this.signUpForm.valid && this.profilePicture) {
      console.log(this.signUpForm.value);
      console.log(this.profilePicture);
    }
  }
}
