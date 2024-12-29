import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {NgForOf} from "@angular/common";

import {Advisor} from "../../../user/models/advisor.model";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {UserApiService} from "../../../user/services/user-api.service";


@Component({
  selector: 'app-view-advisors-search',
  standalone: true,
  imports: [
    MatCardModule, MatToolbarModule, MatFormFieldModule,
    MatButtonModule, MatInputModule, NgForOf
  ],
  templateUrl: './view-advisors-search.component.html',
  styleUrl: './view-advisors-search.component.css'
})
export class ViewAdvisorsSearchComponent implements OnInit{
  advisors: Advisor[] = [];
  advisorDetails: any = {};
  constructor(
    private advisorApiService: AdvisorApiService,
    private userApiService: UserApiService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAdvisors();
  }

  getAdvisors(){
    this.advisorApiService.getAll().subscribe(advisors => {
      this.advisors = advisors;
      this.advisors.forEach(advisor => {
        this.advisorDetails[advisor.userId] = {
          fullname: advisor.fullname,
          location: advisor.location
        };
      });
    });
  }

  filter(event: Event){
    const inputElement = event.target as HTMLInputElement;
    const filteredValue = inputElement.value.replace(/[^a-zA-Z ]/g, '');

    if (filteredValue === '') {
      this.getAdvisors();
    } else {
      this.advisorApiService.getAll().subscribe(res => {
          this.advisors = res.filter(advisor =>
            this.advisorDetails[advisor.userId]?.fullname.toLowerCase().startsWith(filteredValue.toLowerCase())
          );
        },
        error => {
          console.log(error);
        });
    }
  }

  navigateToAdvisorsSearch() {
    this.router.navigate([`criador/buscar-asesor`]);
  }
  navigateToMyAdvisors() {
    this.router.navigate([`criador/mis-asesores`]);
  }
  navigateToAdvisorInfo(id_asesor: number) {
    this.router.navigate([`criador/asesor-info/${id_asesor}`]);
  }
}
