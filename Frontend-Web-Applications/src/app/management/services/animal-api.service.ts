import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";


//Import the BaseService
import {BaseService} from "../../shared/services/base.service";
import {Animal} from "../models/animal.model";
@Injectable({
  providedIn: 'root'
})
export class AnimalApiService extends BaseService<Animal>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.animalURL;
  }
}
