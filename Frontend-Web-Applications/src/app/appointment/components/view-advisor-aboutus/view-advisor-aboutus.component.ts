import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";

import {Advisor} from "../../../user/models/advisor.model";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {NgForOf, NgIf} from "@angular/common";

import {ReviewApiService} from "../../services/review-api.service";
import {Review} from "../../models/review.model";
import {UserApiService} from "../../../user/services/user-api.service";
import {AppointmentApiService} from "../../services/appointment-api.service";
import {Appointment} from "../../models/appointment.model";

@Component({
  selector: 'app-view-advisor-aboutus',
  standalone: true,
  imports: [
    MatCardModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './view-advisor-aboutus.component.html',
  styleUrl: './view-advisor-aboutus.component.css'
})
export class ViewAdvisorAboutusComponent implements OnInit{


  advisor!: Advisor;
  appointments: Appointment[] = [];
  reviews: Review[] = [];
  advisorDetails = {
    fullname: "",
    location: "",
    occupation: "",
    description: ""
  };
  id = 0;
  constructor(
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private advisorApiService: AdvisorApiService,
    private appointmentApiService: AppointmentApiService,
    private userApiService: UserApiService,
    private reviewApiService: ReviewApiService
  )
  {  }

  ngOnInit(): void {
    this.id = +this.activatedRouter.snapshot.params['id'];
    this.getAdvisor();
  }

  getAdvisor(): void {
    this.advisorApiService.getOne(this.id).subscribe(advisor => {
      this.advisor = advisor
      this.advisorDetails = {
        fullname: advisor.fullname,
        location: advisor.location,
        occupation: advisor.occupation,
        description: advisor.description
      };

      this.appointmentApiService.getAll().subscribe(appointments => {
        this.appointments = appointments.filter(appointment => appointment.advisorId === advisor.id);
        this.getAdvisorReviews();
      });
    });
  }

  getAdvisorReviews(): void {
    this.reviewApiService.getAll().subscribe(reviews => {
      this.reviews = reviews.filter(review => {
        return this.appointments.find(appointment => appointment.id === review.appointmentId);
      });
    });
  }

  NavigateToReserveAppointment(): void {
    let id = this.advisor.id;
    this.router.navigate([`/criador/asesor-info/${id}/reservar-cita`]);
  }
  goBack(): void {
    window.history.back();
  }
}
