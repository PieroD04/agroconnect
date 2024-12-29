import {Component, Input, OnInit} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import { Router } from "@angular/router";
import {Client} from "../../models/client.model";
import {AppointmentApiService} from "../../services/appointment-api.service";
import {Appointment} from "../../models/appointment.model";
import {MatDialog} from "@angular/material/dialog";
import {Observable} from "rxjs";
import {
  ConfirmationDialogComponent
} from "../../../public/components/confirmation-dialog/confirmation-dialog.component";

@Component({
  selector: 'client-card',
  standalone: true,
    imports: [
        MatButton,
        MatCard,
        MatCardContent,
        MatCardHeader,
        MatCardTitle
    ],
  templateUrl: './client-card.component.html',
  styleUrl: './client-card.component.css'
})
export class ClientCardComponent {
  @Input() client!: Client;

  constructor(private router: Router,
              private appointmentService: AppointmentApiService,
              private dialog: MatDialog) { }

  goToAppointment(id: any) {
    this.router.navigateByUrl(`/asesor/clientes/${id}`);
  }

  confirmFinished(): Observable<boolean> {
    // Open a dialog to confirm the deletion
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: `¿Estás seguro de querer marcar esta cita como terminada?`
      }
    });
    // Return the result of the dialog
    return dialogRef.afterClosed();
  }

  finishAppointment() {
    this.confirmFinished().subscribe(confirmado => {
      if(confirmado) {
        this.appointmentService.getOne(this.client.appointmentId).subscribe((appointment: Appointment) => {
          appointment.status = "TERMINADO";
          this.appointmentService.update(appointment.id, appointment).subscribe(() => {
            this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
              this.router.navigate([decodeURI(this.router.url)]);
            });
          });
        });
      }
    });

  }
}
