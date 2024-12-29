import {Component, OnInit} from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import {ActivatedRoute} from "@angular/router";
import {MatButton} from "@angular/material/button";
import {DatePipe} from "@angular/common";
import {Breeder} from "../../../user/models/breeder.model";
import {BreederApiService} from "../../../user/services/breeder-api.service";
import {AppointmentApiService} from "../../services/appointment-api.service";
import {Appointment} from "../../models/appointment.model";
import {Client} from "../../models/client.model";
import {CageApiService} from "../../../management/services/cage-api.service";

@Component({
  selector: 'app-client-detail',
  standalone: true,
  imports: [
    MatCardModule,
    MatButton,
    DatePipe
  ],
  templateUrl: './client-detail.component.html',
  styleUrl: './client-detail.component.css'
})
export class ClientDetailComponent implements OnInit{
  breeder!: Breeder;
  appointment: Appointment = {
    id: 0,
    advisorId: 0,
    breederId: 0,
    date: '',
    status: '',
  }

  client: Client = {
    id: 0,
    appointmentId: 0,
    appointmentStatus: '',
    fullname: '',
    location: '',
    cages: 0,
    description: ''
  }
  appointmentId = 0;

  constructor(private breederService: BreederApiService,
              private appointmentService: AppointmentApiService,
              private cageService: CageApiService,
              private activatedRouter: ActivatedRoute) {}

  ngOnInit() {
    this.appointmentId = this.activatedRouter.snapshot.params['id'];
    this.getAppointment();
  }

  getClient(breederId: number) {
    this.breederService.getOne(breederId).subscribe((breeder: Breeder) => {
      this.breeder = breeder;
        this.cageService.getAll().subscribe(cages => {
          this.client = {
            id: breederId,
            appointmentId: this.appointmentId,
            appointmentStatus: this.appointment.status,
            fullname: breeder.fullname,
            location: breeder.location,
            cages: cages.filter(cage => cage.breederId === breederId).length,
            description: breeder.description
          }
        });
    });
  }

  getAppointment() {
    this.appointmentService.getOne(this.appointmentId).subscribe((appointment: Appointment) => {
      this.appointment = appointment;
      this.getClient(appointment.breederId);
    });
  }

  goBack() {
    window.history.back();
  }

}
