import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { textValidator } from '../../../../../shared/validators/text.validator';
import { Store } from '@ngrx/store';
import { getRestaurantsByText } from '../../../../store/restaurants.actions';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-search-field',
  templateUrl: './restaurant-search-field.component.html',
  styleUrls: ['./restaurant-search-field.component.scss'],
})
export class RestaurantSearchFieldComponent {
  formGroup = new FormGroup<{ search: FormControl }>({
    search: new FormControl('', [textValidator]),
  });

  constructor(private store: Store, private router: Router) {}

  searchRestaurants() {
    if (this.formGroup.controls.search.valid) {
      const text = this.formGroup.value.search;
      this.store.dispatch(getRestaurantsByText({ text }));
      this.router.navigate([
        '/restaurants/all/search/' + text.replace(' ', '_'),
      ]);
      this.formGroup.reset();
    }
  }
}
