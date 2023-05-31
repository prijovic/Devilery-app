import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-working-hours-field',
  templateUrl: './working-hours-field.component.html',
  styleUrls: ['./working-hours-field.component.scss']
})
export class WorkingHoursFieldComponent implements OnInit {
  @Output() opensAtChanged: EventEmitter<string> = new EventEmitter<string>();
  @Output() closesAtChanged: EventEmitter<string> = new EventEmitter<string>();
  workingHoursForm: FormGroup<{opensAt: FormControl, closesAt: FormControl}> = new FormGroup({
    opensAt: new FormControl(null, [Validators.required]),
    closesAt: new FormControl(null, [Validators.required]),
  });

  ngOnInit() {
    this.workingHoursForm.controls.opensAt.valueChanges.subscribe(value => this.opensAtChanged.emit(value));
    this.workingHoursForm.controls.closesAt.valueChanges.subscribe(value => this.closesAtChanged.emit(value));
  }
}
