import { Component, OnInit } from '@angular/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import { ActivatedRoute, Router} from "@angular/router";

import dayGridPlugin from '@fullcalendar/daygrid';
import esLocale from '@fullcalendar/core/locales/es';
import {MatCardModule} from "@angular/material/card";

import {AppointmentApiService} from "../../services/appointment-api.service";
import {Appointment} from "../../models/appointment.model";

import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {BreederApiService} from "../../../user/services/breeder-api.service";
import {Advisor} from "../../../user/models/advisor.model";
import {Breeder} from "../../../user/models/breeder.model";
import {forkJoin, map, Observable, switchMap, window} from "rxjs";

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    FullCalendarModule,
    MatCardModule,
  ],
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit{
  breeder_id = 0;
  advisor_id= 0;
  userType: string = '';
  fullnames: string[] = [];

  calendarOptions: { plugins: any[], initialView: string, locale: any, events: { title: string, start: string, color: string, extendedProps: { name: string, time: string } }[], eventContent: (arg: any) => any } = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth',
    locale: esLocale,
    events: [],
    eventContent: (arg) => {
      return {
        html: `
            ${arg.event.title} <br>
            ${arg.event.extendedProps.name} <br>
            Hora: ${arg.event.extendedProps.time}
        `
      };
    }
  };
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private appointmentApiService: AppointmentApiService,
    private breederApiService: BreederApiService,
    private advisorApiService: AdvisorApiService
  ){}

  ngOnInit() {
    this.breeder_id = this.breederApiService.getBreederId();
    this.advisor_id = this.advisorApiService.getAdvisorId();
    this.determineUserType();
    this.fetchAppointments();
  }

  // Método para determinar el tipo de usuario
  determineUserType() {
    this.route.url.subscribe(segments => {
      if (segments.some(segment => segment.path === 'criador')) {
        this.userType = 'breeder';
      } else if (segments.some(segment => segment.path === 'asesor')) {
        this.userType = 'advisor';
      }
    });
  }

  // Marca en el calendario las citas del asesor o del criador (solo muestra las citas con el status = "Pendiente")
  fetchAppointments() {
    if (this.userType === 'advisor') {
      this.advisorApiService.getAppointmentsByAdvisorId(this.advisor_id).subscribe(
        (appointments) => {
          forkJoin(
            appointments.map(appointment => this.createEventFromAppointment(appointment))
          ).subscribe(events => {
            this.calendarOptions.events = events;
          });
        }
      );
    }
    else {
      this.breederApiService.getAppointmentsByBreederId(this.breeder_id).subscribe(
        (appointments) => {
          forkJoin(
            appointments.map(appointment => this.createEventFromAppointment(appointment))
          ).subscribe(events => {
            this.calendarOptions.events = events;
          });
        }
      );
    }

  }


  getFullnameFromAdvisorOrBreederId(id: number, userType: string): Observable<string> {
    let userFn$: Observable<string>;
    if (userType === 'advisor') {
      userFn$ = this.advisorApiService.getOne(id).pipe(
        map((advisor: Advisor) => advisor.fullname));
    } else {
      userFn$ = this.breederApiService.getOne(id).pipe(
        map((breeder: Breeder) => breeder.fullname));
    }
    return userFn$.pipe(

    );
  }

  // Crea el evento osea lo que marca en el calendario
  createEventFromAppointment(appointment: Appointment): Observable<any> {
    return this.getFullnameFromAdvisorOrBreederId(
      this.userType === 'advisor' ? appointment.breederId : appointment.advisorId,
      this.userType === 'advisor' ? 'breeder' : 'advisor'
    ).pipe(
      map(fullname => {
        const [date, time] = appointment.date.split('T');
        const dateObj = new Date(appointment.date);
        const formattedTime = `${dateObj.getUTCHours()}:${dateObj.getUTCMinutes() < 10 ? '0' : ''}${dateObj.getUTCMinutes()}`;
        return {
          title: 'Cita con:',
          start: date,
          color: '#DD763D',
          extendedProps: {
            name: fullname,
            time: formattedTime
          }
        };
      })
    );
  }

  protected readonly window = window;
}
