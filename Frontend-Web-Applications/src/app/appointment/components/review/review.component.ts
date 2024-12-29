import {Component, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {Review} from "../../models/review.model";
import {ActivatedRoute, Router} from "@angular/router";
import {MatIcon} from "@angular/material/icon";
import {NgForOf, NgIf} from "@angular/common";

import {Advisor} from "../../../user/models/advisor.model";
import {Appointment} from "../../models/appointment.model";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {AppointmentApiService} from "../../services/appointment-api.service";
import {ReviewApiService} from "../../services/review-api.service";
import {MatCardModule} from "@angular/material/card";

@Component({
  selector: 'app-review',
  standalone: true,
  imports: [
    FormsModule, MatButton, MatFormField, MatInput, MatLabel,
    ReactiveFormsModule, MatIcon, NgForOf, NgIf, MatCardModule,
  ],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {
  isConfirmed: boolean = false;
  advisor!: Advisor;
  advisorDetails: any = {
    fullname: "",
    location: ""
  };
  appointment!: Appointment;

  rating: number = 0;
  stars: boolean[] = Array(5).fill(false);

  review: Review = {
    id: 0,
    appointmentId: 0,
    comment: "",
    rating: 0
  }

  constructor(
    private activatedRoute: ActivatedRoute,
    private advisorService: AdvisorApiService,
    private appointmentService: AppointmentApiService,
    private reviewService: ReviewApiService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.review.appointmentId = params['id'];

      const appointmentId = params['id'];
      this.getAppointment(appointmentId);
    });
  }

  getAppointment(appointmentId: string): void {
    this.appointmentService.getOne(appointmentId).subscribe(appointment => {
      this.appointment = appointment;
      this.getAdvisor();
    });
  }

  getAdvisor(): void {
    const advisorId = this.appointment.advisorId;
    this.advisorService.getOne(advisorId).subscribe(advisor => {
      this.advisor = advisor;
      this.advisorDetails = {
        fullname: advisor.fullname,
        location: advisor.location
      };
    });

  }
  onSubmit() {
    this.review.rating = this.rating;
    this.review.appointmentId = this.appointment.id;
    this.reviewService.create(this.review).subscribe();
    this.appointment.status = "TERMINADO";
    this.appointmentService.update(this.appointment.id, this.appointment).subscribe();
    this.isConfirmed = true;
  }

  goHome(){
    this.isConfirmed = false;
    this.router.navigate(['/criador/mis-asesores']);
  }
  onCancel(){
    this.isConfirmed = false;
    this.router.navigate(['/criador/mis-asesores']);

  }

  // CALIFICACION POR ESTRELLAS
  onStarClick(index: number): void {
    this.rating = index + 1;
    this.updateStars();
  }
  updateStars(): void {
    this.stars.fill(false);
    this.stars.fill(true, 0, this.rating);
  }

}
