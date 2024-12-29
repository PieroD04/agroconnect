import {Component, OnInit} from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PublicationsApiService } from "../../services/publications-api.service";
import { Publication } from '../../models/publication.model';
import { Router } from '@angular/router';
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import { MatSnackBar } from '@angular/material/snack-bar';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {MatIcon} from "@angular/material/icon";
import {NgIf} from "@angular/common";
import {StorageService} from "../../../shared/services/storage.service";

@Component({
  selector: 'app-new-publication',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInput,
    MatButton,
    MatIcon,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './new-publication.component.html',
  styleUrl: './new-publication.component.css'
})
export class NewPublicationComponent implements OnInit {
  publicationForm : FormGroup = new FormGroup(
    {
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    }
  );
  image: any;
  advisorId = 0;
  selectedFileName = '';

  constructor(private publicationsService: PublicationsApiService,
              private advisorService: AdvisorApiService,
              private router: Router,
              private snackBar: MatSnackBar,
              private storageService: StorageService) {
  }

  ngOnInit() {
    this.advisorId = this.advisorService.getAdvisorId();
  }

  uploadImage(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      this.selectedFileName = file.name;
      console.log(file);
      let reader= new FileReader();
      let name = "PUBLICATION_" + this.advisorId + "_IMAGE_" + Date.now();
      reader.readAsDataURL(file);
      reader.onloadend = () => {
        console.log(reader.result);
        this.storageService.uploadFile(name, reader.result).then((url) => {
          console.log(url);
          this.image = url;
        });
      }
    }
  }

  onSubmit() {
    if(this.selectedFileName === '') {
      this.snackBar.open('Debes seleccionar una imagen para la publicación😓', 'Cerrar', {
        duration: 5000,
      });
      return;
    }
    if(this.image === null) {
      this.snackBar.open('Error al subir la imagen de la publicación😥', 'Cerrar', {
        duration: 5000,
      });
      return;
    }
    // obtener fecha actual en formato 2024-04-30T11:10:52Z
    let currentDate = new Date();
    let formattedDate = currentDate.toISOString();
    formattedDate = formattedDate.slice(0, -5) + 'Z';

    let newPublication: Publication = {
      id: 0,
      advisorId: this.advisorId,
      title: this.publicationForm.value.title,
      description: this.publicationForm.value.description,
      date: formattedDate,
      image: this.image
    }

    this.publicationsService.create(newPublication).subscribe(() => {
      this.snackBar.open('Publicación creada con éxito🤩', 'Cerrar', {
        duration: 5000,
      });
      this.goToPublications();
    }, error => {
      this.snackBar.open('Error al crear la publicación😥', 'Cerrar', {
        duration: 5000,
      });
      console.error(error);
    });
  }


  goBack() {
    window.history.back();
  }

  goToPublications() {
    this.router.navigate(['/asesor/mis-publicaciones']);
  }

}
