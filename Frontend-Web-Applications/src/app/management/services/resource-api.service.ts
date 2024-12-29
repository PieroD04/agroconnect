import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

//Import the resource model
import {Resource} from "../models/resource.model";

//Import the BaseService
import {BaseService} from "../../shared/services/base.service";
@Injectable({
  providedIn: 'root'
})
export class ResourceApiService extends BaseService<Resource>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.resourceURL;
  }
}
