import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {PictureService} from "../../../../services/picture.service";
import {Store} from "@ngrx/store";
import {signUp} from "../../../store/auth.actions";
import {map} from "rxjs";
import {
  FormWithImageInputComponent
} from "../../../../shared/components/form-with-image-input/form-with-image-input.component";

@Component({
  selector: 'app-sign-up-form',
  templateUrl: './sign-up-form.component.html',
  styleUrls: ['./sign-up-form.component.scss'],
})
export class SignUpFormComponent extends FormWithImageInputComponent implements OnInit {
  signUpForm!: FormGroup;

  constructor(private pictureService: PictureService, private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      surname: new FormControl(null, [Validators.required]),
      phoneNumber: new FormControl(null, [Validators.required]),
    });
  }

  signUp() {
    if (this.signUpForm.valid && this.picture) {
      const signUpRequest = this.signUpForm.value;
      this.pictureService.uploadPicture(this.picture).pipe(
        map(response => response.fileName)
      ).subscribe(
        profilePicture => {
          signUpRequest.profilePicture = profilePicture;
          this.store.dispatch(signUp(signUpRequest))
        });
    }
  }
}
