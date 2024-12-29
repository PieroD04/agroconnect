import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

//Import the BaseService
import {BaseService} from "../../shared/services/base.service";
import {Cage} from "../models/cage.model";
import {catchError} from "rxjs";
import {Animal} from "../models/animal.model";
@Injectable({
  providedIn: 'root'
})
export class CageApiService extends BaseService<Cage>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.cageURL;
  }

  getAnimalsByCageId(cageId: number){
    this.setToken();
    return this.http.get<Animal[]>(this.buildPath() + '/' + cageId + '/animals', this.httpOptions).pipe(catchError(this.handleError));
  }
}
