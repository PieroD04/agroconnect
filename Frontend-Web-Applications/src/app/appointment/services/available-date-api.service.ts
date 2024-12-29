import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";


//Import the BaseService
import {BaseService} from "../../shared/services/base.service";
import {AvailableDate} from "../models/available_date.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AvailableDateApiService extends BaseService<AvailableDate>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.availableDateURL;
  }



}
