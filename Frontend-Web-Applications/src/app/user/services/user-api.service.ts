import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

//Import the user model
import {User} from "../models/user.model";
import {BaseService} from "../../shared/services/base.service";
import {Notification} from "../../appointment/models/notification.model";
import {catchError, Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserApiService extends  BaseService<User>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.userURL;
  }

  setLogged(isLogged: boolean){
    // Check if the window object is defined (prevent error from server side rendering)
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('isLogged', String(isLogged));
    }
  }

  isLogged() {
    if (typeof window !== 'undefined' && window.localStorage) {
      const logged = localStorage.getItem('isLogged');
      return logged === 'true';
    }
    return false;
  }

  setIsBreeder(isBreeder: boolean) {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('isBreeder', String(isBreeder));
    }
  }

  getIsBreeder(): boolean {
    if (typeof window !== 'undefined' && window.localStorage) {
      const isBreeder = localStorage.getItem('isBreeder');
      return isBreeder === 'true';
    }
    return false;
  }

  setUserId(user_id: number) {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('user_id', user_id.toString());
    }
  }

  getUserId(): number {
    if (typeof window !== 'undefined' && window.localStorage) {
      const user_id = localStorage.getItem('user_id');
      return user_id ? parseInt(user_id) : 0;
    }
    return 0;
  }

  getNotificationsByUserId(user_id: number) {
    this.setToken();
    return this.http.get<Notification[]>(this.buildPath() + '/' + user_id + '/notifications', this.httpOptions).pipe(catchError(this.handleError));
  }

}
