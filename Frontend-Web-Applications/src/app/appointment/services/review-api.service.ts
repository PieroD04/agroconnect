import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

import {Review} from "../models/review.model";
//Import the BaseService
import {BaseService} from "../../shared/services/base.service";



@Injectable({
  providedIn: 'root'
})
export class ReviewApiService extends BaseService<Review>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.reviewURL;
  }

}
