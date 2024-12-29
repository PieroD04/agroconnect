import {Component, OnInit} from '@angular/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatButton } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { CommonModule } from '@angular/common';
import { HttpClientModule } from "@angular/common/http";

import {HeaderComponent} from "../../../public/components/header/header.component";
import {EmptyViewComponent} from "../../../public/components/empty-view/empty-view.component";
import {FormsModule} from "@angular/forms";
import {SidenavComponent} from "../../../public/components/sidenav/sidenav.component";


//Import the NotificationApiService

import { Router } from "@angular/router";
import {NotificationApiService} from "../../services/notification-api.service";
import {Notification} from "../../models/notification.model";
import {UserApiService} from "../../../user/services/user-api.service";


@Component({
  selector: 'app-notifications-view-advisor-view',
  standalone: true,
  imports: [
    MatRadioModule,
    MatButton,
    MatCardModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    EmptyViewComponent,
    HeaderComponent,
    SidenavComponent,
    MatIconModule
  ],
  templateUrl: './notifications-view.component.html',
  styleUrl: './notifications-view.component.css'
})
export class NotificationsViewComponent implements OnInit{
  results: any[] = [];
  user_id = 0;

  constructor(private notificationsApiService: NotificationApiService,
              private userApiService: UserApiService) {
  }

  ngOnInit(): void {
    this.user_id = this.userApiService.getUserId();
    this.getNotifications();
  }

  getNotifications() {
    this.userApiService.getNotificationsByUserId(this.user_id).subscribe((notifications: Notification[]) => {
      notifications.forEach((notification: Notification) => {
        // Create a new date object
        const date = new Date(notification.date);
        // Apply the format to the date
        const formattedDate = date.toLocaleDateString('es-ES', { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' });

        this.results.push({
          id: notification.id,
          type: notification.type,
          text: notification.text,
          date: formattedDate
        });
      });
    }, (error) => {
      console.error(error);
    });
  }

  goToMeeting(id: number) {
    this.notificationsApiService.getOne(id).subscribe((notification) => {
      window.open(notification.meetingUrl, '_blank');
    })
  }

  deleteNotification(id: number) {
    this.notificationsApiService.delete(id).subscribe(() => {
      console.log("Notificación eliminada con éxito.");
      this.results = this.results.filter((notification: any) => notification.id !== id);
    }, (error) => {
      console.error("Error al eliminar la notificación:", error);
    });
  }
}
