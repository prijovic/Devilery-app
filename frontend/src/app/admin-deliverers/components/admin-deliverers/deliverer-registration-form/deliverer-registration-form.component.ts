import {Component, OnInit} from '@angular/core';
import {
  FormWithImageInputComponent
} from "../../../../shared/components/form-with-image-input/form-with-image-input.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PictureService} from "../../../../services/picture.service";
import {Store} from "@ngrx/store";
import {map} from "rxjs";
import {registerDeliverer} from "../../../store/admin-deliverers.actions";

@Component({
  selector: 'app-deliverer-registration-form',
  templateUrl: './deliverer-registration-form.component.html',
  styleUrls: ['./deliverer-registration-form.component.scss']
})
export class DelivererRegistrationFormComponent extends FormWithImageInputComponent implements OnInit {
  delivererRegistrationForm!: FormGroup;

  constructor(private pictureService: PictureService, private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.delivererRegistrationForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      surname: new FormControl(null, [Validators.required]),
      phoneNumber: new FormControl(null, [Validators.required]),
      delivererType: new FormControl(null, [Validators.required])
    });
  }

  registerDeliverer() {
    if (this.delivererRegistrationForm.valid && this.picture) {
      const delivererRegistrationRequest = this.delivererRegistrationForm.value;
      this.pictureService.uploadPicture(this.picture).pipe(
        map(response => response.fileName)
      ).subscribe(
        profilePicture => {
          delivererRegistrationRequest.profilePicture = profilePicture;
          this.store.dispatch(registerDeliverer(delivererRegistrationRequest))
        });
    }
  }
}
