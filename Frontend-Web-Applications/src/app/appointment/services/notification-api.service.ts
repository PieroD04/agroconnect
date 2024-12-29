import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

//Import the notification model
import {Notification} from "../models/notification.model";
//Import the BaseService
import {BaseService} from "../../shared/services/base.service";

@Injectable({
  providedIn: 'root'
})
export class NotificationApiService extends BaseService<Notification> {
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.notificationURL;
  }
}
