import {Component, OnInit} from '@angular/core';
import {
  FormWithImageInputComponent
} from "../../../../shared/components/form-with-image-input/form-with-image-input.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {map} from "rxjs";
import {PictureService} from "../../../../services/picture.service";
import {registerRestaurant} from "../../../store/admin-restaurants.actions";
import {Store} from "@ngrx/store";

@Component({
  selector: 'app-restaurant-registration-form',
  templateUrl: './restaurant-registration-form.component.html',
  styleUrls: ['./restaurant-registration-form.component.scss']
})
export class RestaurantRegistrationFormComponent extends FormWithImageInputComponent implements OnInit {
  restaurantRegistrationForm!: FormGroup;

  constructor(private pictureService: PictureService, private store: Store) {
    super();
  }

  ngOnInit(): void {
    this.restaurantRegistrationForm = new FormGroup({
      name: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required]),
      address: new FormControl(null, [Validators.required]),
      opensAt: new FormControl(null, [Validators.required]),
      closesAt: new FormControl(null, [Validators.required]),
      minPreparation: new FormControl(null, [Validators.required]),
      maxPreparation: new FormControl(null, [Validators.required]),
      minOrder: new FormControl(null, [Validators.required]),
    });
  }

  registerRestaurant() {
    if (this.restaurantRegistrationForm.valid && this.picture) {
      let restaurantRegistrationRequest = this.restaurantRegistrationForm.value;
      restaurantRegistrationRequest = {
        ...restaurantRegistrationRequest,
        ...restaurantRegistrationRequest.address,
        opensAt: restaurantRegistrationRequest.opensAt + ":00",
        closesAt: restaurantRegistrationRequest.closesAt + ":00"
      }
      delete restaurantRegistrationRequest.address;
      console.log(restaurantRegistrationRequest);
      this.pictureService.uploadPicture(this.picture).pipe(
        map(response => response.fileName)
      ).subscribe(
        picture => {
          restaurantRegistrationRequest.picture = picture;
          this.store.dispatch(registerRestaurant(restaurantRegistrationRequest))
        });
    }
  }
}
