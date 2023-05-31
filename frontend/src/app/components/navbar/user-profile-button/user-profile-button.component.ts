import {Component, Inject, OnInit} from '@angular/core';
import {SafeUrl} from "@angular/platform-browser";
import {DOCUMENT} from "@angular/common";
import {PictureService} from "../../../services/picture.service";
import {Store} from "@ngrx/store";
import {logout} from "../../../auth/store/auth.actions";

@Component({
  selector: 'app-user-profile-button',
  templateUrl: './user-profile-button.component.html',
  styleUrls: ['./user-profile-button.component.scss']
})
export class UserProfileButtonComponent implements OnInit {
  image!: SafeUrl;

  constructor(@Inject(DOCUMENT) private document: Document, private pictureService: PictureService, private store: Store) {}

  ngOnInit(): void {
    this.pictureService.getSelfProfilePicture().subscribe(profilePicture => {
      if (document.defaultView && profilePicture.size > 0) {
        this.image = document.defaultView.URL.createObjectURL(profilePicture);
      }
  }
    );
  }

  getBackgroundImageUrl() {
    return `url("${this.image}")`;
  }

  logout() {
    this.store.dispatch(logout());
  }
}
