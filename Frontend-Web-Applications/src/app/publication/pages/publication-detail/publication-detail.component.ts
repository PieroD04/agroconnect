import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { PublicationsApiService } from "../../services/publications-api.service";
import {ActivatedRoute} from "@angular/router";
import {Publication} from "../../models/publication.model";
import {MatButton} from "@angular/material/button";
import {DatePipe} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";

// Import the ConfirmationDialogComponent
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from "../../../public/components/confirmation-dialog/confirmation-dialog.component";
import { Observable } from "rxjs";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-publication-detail',
  standalone: true,
  imports: [
    MatCardModule,
    MatButton,
    DatePipe,
    MatIcon
  ],
  templateUrl: './publication-detail.component.html',
  styleUrl: './publication-detail.component.css'
})
export class PublicationDetailComponent {
  publication = {} as Publication;

  constructor(private publicationsService: PublicationsApiService,
              private activatedRouter: ActivatedRoute,
              private snackBar: MatSnackBar,
              private dialog: MatDialog) {
    this.activatedRouter.params.subscribe(
      params => {
        this.getPublication(params['id']);
      }
    );
  }

  getPublication(id: any) {
    this.publicationsService.getOne(id).subscribe((publication: Publication) => {
      this.publication = publication;
    });
  }

  confirmDeletion(): Observable<boolean> {
    // Open a dialog to confirm the deletion
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: `¿Estás seguro de querer eliminar esta publicación? Los criadores ya no podrán visualizarla.`
      }
    });
    // Return the result of the dialog
    return dialogRef.afterClosed();
  }

  deletePublication(id: any) {
    // First, confirm the deletion
    this.confirmDeletion().subscribe(confirmado => {
      if (confirmado) {
        // If the deletion is confirmed, delete the publication
        this.publicationsService.delete(id).subscribe(() => {
            this.snackBar.open('Publicación eliminada con éxito😎', 'Cerrar', {
              duration: 2000,
            }).afterDismissed().subscribe(() => { this.goBack() });
          }, error => {
            this.snackBar.open('Error al eliminar la publicación', 'Cerrar', {
              duration: 2000,
            });
          }
        );
      }
    })
  }

  goBack() {
    window.history.back();
  }
}
