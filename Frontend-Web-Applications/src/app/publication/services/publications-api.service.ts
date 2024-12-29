import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from "@angular/common/http";
import { Publication } from "../models/publication.model";

import {BaseService} from "../../shared/services/base.service";

@Injectable({
  providedIn: 'root'
})

export class PublicationsApiService extends BaseService<Publication>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.publicationURL;
  }
}
