import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

import {Appointment} from "../models/appointment.model";
//Import the BaseService
import {BaseService} from "../../shared/services/base.service";


@Injectable({
  providedIn: 'root'
})
export class AppointmentApiService extends BaseService<Appointment>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.appointmentURL;
  }

}
