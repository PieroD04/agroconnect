import {Component, Input} from '@angular/core';
import {Publication} from "../../models/publication.model";
import {MatCardModule} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {Router} from "@angular/router";
import {PublicationsApiService} from "../../services/publications-api.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'publication-card',
  standalone: true,
  imports: [
    MatCardModule,
    MatButton
  ],
  templateUrl: './publication-card.component.html',
  styleUrl: './publication-card.component.css'
})
export class PublicationCardComponent {
  @Input() publication !: Publication;
  constructor(private router: Router,
              private publicationService: PublicationsApiService,
              private snackBar: MatSnackBar) { }
  getPublication(id: any) {
    this.router.navigateByUrl(`asesor/mis-publicaciones/${id}`);
  }

}
