import { Injectable } from '@angular/core';
import {BaseService} from "../../shared/services/base.service";
import {User} from "../../user/models/user.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {catchError, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationApiService extends BaseService<User> {
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.authenticationURL;
  }

  signUp(username: string, password: string, role: string) {
    const user = {
      "username": username,
      "password": password,
      "roles": [role]
    };
    return this.http.post(this.buildPath() + '/sign-up', user, this.httpOptions)
      .pipe(catchError(this.handleError));
  }

  signIn(username: string, password: string) {
    const user = {
      "username": username,
      "password": password
    };
    return this.http.post(this.buildPath() + '/sign-in', user, this.httpOptions)
      .pipe(catchError(this.handleError))
      .pipe(tap((response: any) => {
        this.newToken(response["token"]);
      }))
  }

}
