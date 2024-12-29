import {Component, OnInit, ViewChild} from '@angular/core';
import {MatError, MatFormField, MatLabel} from "@angular/material/form-field";
import {MatButton} from "@angular/material/button";
import {RouterLink} from "@angular/router";
import {MatInput} from "@angular/material/input";
import {FormBuilder, FormGroup, NgForm, ReactiveFormsModule, Validators} from "@angular/forms";
import { MatSnackBar } from '@angular/material/snack-bar'; // Importa MatSnackBar
import { Router } from '@angular/router'; // Importa Router
import { AvailableDate } from "../../models/available_date.model";
import { AvailableDateApiService } from "../../services/available-date-api.service";
import { AdvisorApiService } from "../../../user/services/advisor-api.service";
import {NgIf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {Publication} from "../../../publication/models/publication.model";


@Component({
  selector: 'app-add-availability-schedule',
  standalone: true,
    imports: [
        MatFormField,
        MatButton,
        MatLabel,
        RouterLink,
        MatInput,
        ReactiveFormsModule,
        MatError,
        NgIf,
        MatIcon
    ],
  templateUrl: './add-availability-schedule.component.html',
  styleUrl: './add-availability-schedule.component.css'
})
export class AddAvailabilityScheduleComponent implements OnInit{
  form !: FormGroup;
  availableDateData!: AvailableDate;
  advisorId = 0;

  constructor(private availableDateService: AvailableDateApiService,
              private advisorService: AdvisorApiService,
              private router: Router,
              private snackBar: MatSnackBar) {
    this.availableDateData = {} as AvailableDate;
  }

  ngOnInit() {
    this.advisorId = this.advisorService.getAdvisorId();
    this.form = new FormBuilder().group({
      date: ['', Validators.required], // Add this line
      startTime: ['', Validators.required],
      endTime: ['', Validators.required]
    }, { validators: [this.validTimeValidator, this.validDateValidator] });
  }

  addAvailableDate() {
    let formattedDate = this.form.get('date')?.value;

    let startTimeParts = this.form.get('startTime')?.value.split(':');
    let endTimeParts = this.form.get('endTime')?.value.split(':');

    let newAvailableDate = {
      advisorId: this.advisorId,
      date: formattedDate,
      startTime: {
        hour: parseInt(startTimeParts[0]),
        minute: parseInt(startTimeParts[1]),
        second: 0,
        nano: 0
      },
      endTime: {
        hour: parseInt(endTimeParts[0]),
        minute: parseInt(endTimeParts[1]),
        second: 0,
        nano: 0
      },
      status: true
    }

    this.availableDateService.create(newAvailableDate).subscribe(() => {
      this.snackBar.open('Horario disponible creado con éxito🎉', 'Cerrar', {
        duration: 5000,
      });
      this.goToAvailableDates();
    }, error => {
      this.snackBar.open('Error al crear el horario disponible😓', 'Cerrar', {
        duration: 5000,
      });
      console.error(error);
    });
  }

  goToAvailableDates() {
    this.router.navigate(['/asesor/horarios']);
  }

  validTimeValidator(formGroup: FormGroup) {
    const startTimeControl = formGroup.get('startTime');
    const endTimeControl = formGroup.get('endTime');
    if (startTimeControl && endTimeControl) {
      const startTime = startTimeControl.value;
      const endTime = endTimeControl.value;
      if (startTime.replace(":", "") >= endTime.replace(":", "")) {
        return { invalidTime: true };
      }
    }
    return null;
  }
  validDateValidator(formGroup: FormGroup) {
    const dateControl = formGroup.get('date');
    if (dateControl) {
      const controlDate = new Date(dateControl.value);
      const currentDate = new Date();
      currentDate.setHours(0, 0, 0, 0); // Set time to 00:00:00.000
      if (controlDate < currentDate) {
        return { 'invalidDate': true };
      }
    }
    return null;
  }

}
