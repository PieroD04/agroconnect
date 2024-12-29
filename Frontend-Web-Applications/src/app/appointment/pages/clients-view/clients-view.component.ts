import {Component, OnInit} from '@angular/core';
import {ClientCardComponent} from "../../components/client-card/client-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {Appointment} from "../../models/appointment.model";
import {Client} from "../../models/client.model";
import {BreederApiService} from "../../../user/services/breeder-api.service";
import {AppointmentApiService} from "../../services/appointment-api.service";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {EmptyViewComponent} from "../../../public/components/empty-view/empty-view.component";

@Component({
  selector: 'app-clients-view',
  standalone: true,
  imports: [
    ClientCardComponent,
    NgForOf,
    EmptyViewComponent,
    NgIf
  ],
  templateUrl: './clients-view.component.html',
  styleUrl: './clients-view.component.css'
})
export class ClientsViewComponent implements OnInit {
  advisorId = 0;
  appointments: Appointment[] = [];
  clients: Client[] = [];

  constructor (private breederService: BreederApiService,
               private advisorService: AdvisorApiService,
               private appointmentService: AppointmentApiService) { }

  ngOnInit() {
    this.advisorId = this.advisorService.getAdvisorId();
    this.getAppointments();
  }

  getAppointments() {
    this.advisorService.getAppointmentsByAdvisorId(this.advisorId).subscribe(appointments => {
      this.appointments = appointments;
      this.getClients();
    }, error => {
        console.log(error);
    });
  }

  getClients(){
    this.appointments.forEach(appointment => {
      this.breederService.getOne(appointment.breederId).subscribe(breeder => {
        let client = {
          id: breeder.id,
          appointmentId: appointment.id,
          fullname: breeder.fullname,
          appointmentStatus: appointment.status,
          location: breeder.location,
          cages: 0,
          description: breeder.description
        };
        this.clients.push(client);
      });
    });
  }
}
