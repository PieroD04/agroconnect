import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import { MatRadioModule } from '@angular/material/radio';

import {Advisor} from "../../../user/models/advisor.model";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";

import {AvailableDate} from "../../models/available_date.model";

import {Breeder} from "../../../user/models/breeder.model";
import {BreederApiService} from "../../../user/services/breeder-api.service";

import {Appointment} from "../../models/appointment.model";
import {AppointmentApiService} from "../../services/appointment-api.service";

import {AvailableDateApiService} from "../../services/available-date-api.service";

import {NgForOf, DatePipe, NgIf} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-view-reserve-appointment',
  standalone: true,
  imports: [
    MatCardModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatRadioModule,
    NgForOf,
    DatePipe,
    NgIf
  ],
  templateUrl: './view-reserve-appointment.component.html',
  styleUrl: './view-reserve-appointment.component.css'
})
export class ViewReserveAppointmentComponent implements OnInit {

  advisor!: Advisor;
  advisor_availableDates: AvailableDate[] = [];
  breeder!: Breeder;
  breeder_id = 0;
  selectedDateIndex!: number;
  appointmentId = 0;
  constructor(
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private advisorApiService: AdvisorApiService,
    private breederApiService: BreederApiService,
    private appointmentApiService: AppointmentApiService,
    private availableDateApiService: AvailableDateApiService,
    private snackBar: MatSnackBar
  ){}

  ngOnInit(): void {
    this.breeder_id = this.breederApiService.getBreederId();
    this.getAdvisor();
    this.getBreeder();
  }

  getAdvisor(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.advisorApiService.getOne(id).subscribe(advisor => {
      this.advisor = advisor;
      this.getAdvisorAvailableDates(); //called after getting advisor
    });

  }
  getAdvisorAvailableDates(): void {
    this.availableDateApiService.getAll().subscribe(dates => {
        dates = dates.filter(date => date.advisorId === this.advisor.id && date.status === true);
        this.advisor_availableDates = dates;
        console.log("Horarios: ",this.advisor_availableDates);
      }
    )
  }
  getBreeder(): void {
    this.breederApiService.getOne(this.breeder_id).subscribe(breeder => this.breeder = breeder);
  }

  setSelectedDateIndex(index: number): void {
    this.selectedDateIndex = index;
  }

  createAppointment(): void{
    if (this.selectedDateIndex === undefined || this.selectedDateIndex < 0 || this.selectedDateIndex >= this.advisor_availableDates.length) {
      console.error('Índice de fecha seleccionada no válido');
      return;
    }

    let selectedDate = this.advisor_availableDates[this.selectedDateIndex];
    let appointmentDate = new Date(`${selectedDate.date}T${selectedDate.startTime}`);
    let newAppointment: Appointment = {
      id: this.appointmentId,
      advisorId: this.advisor.id,
      breederId: this.breeder.id,
      date: appointmentDate,
      status: "PENDIENTE" // TERMINADO, PENDIENTE
  };

    this.appointmentApiService.create(newAppointment).subscribe(() => {
      let selectedDate = this.advisor_availableDates[this.selectedDateIndex];
      let startTime = selectedDate.startTime.split(':').map(Number);
      let endTime = selectedDate.endTime.split(':').map(Number);

      let transformedSelectedDate = {
        advisorId: selectedDate.advisorId,
        date: selectedDate.date,
        startTime: {
          hour: startTime[0],
          minute: startTime[1],
          second: startTime[2],
          nano: 0
        },
        endTime: {
          hour: endTime[0],
          minute: endTime[1],
          second: endTime[2],
          nano: 0
        },
        status: false
      };
      this.availableDateApiService.update(selectedDate.id, transformedSelectedDate).subscribe(() => {
        this.getAdvisorAvailableDates();
        this.snackBar.open('Cita reservada🤩 ¡Revisa tus notificaciones!', 'Cerrar', {
          duration: 2000
        }).afterDismissed().subscribe(() => {
          this.router.navigate(['criador/buscar-asesor']);
        });
      });
    }, error => {
      this.snackBar.open('Error al reservar la cita😥', 'Cerrar', {
        duration: 2000
      });
    });
  }

  // Status 1: Disponible, Status 0: No disponible
  getStatusText(status: boolean): string {
    return status === true ? 'Disponible' : 'No disponible';
  }
  cancel(): void {
    this.router.navigate(['criador/buscar-asesor']);
  }
}
