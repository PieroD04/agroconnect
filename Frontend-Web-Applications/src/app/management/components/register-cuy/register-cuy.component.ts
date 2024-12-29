import { Component } from '@angular/core';
import {MatButton} from "@angular/material/button";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {CommonModule} from "@angular/common";
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../../../public/components/dialog/dialog.component';
import { Animal } from '../../models/animal.model';
import {AnimalApiService} from "../../services/animal-api.service";
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import {MatIcon} from "@angular/material/icon";
import {BreederApiService} from "../../../user/services/breeder-api.service";

@Component({
  selector: 'app-register-cuy',
  standalone: true,
  imports: [
    MatButton,
    RouterLink,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    CommonModule,
    MatRadioGroup,
    MatRadioButton,
    MatOption,
    MatSelect,
    MatIcon
  ],
  templateUrl: './register-cuy.component.html',
  styleUrl: './register-cuy.component.css'
})
export class RegisterCuyComponent {
  animal: Animal = {
    id: 0,
    name: "",
    breed: "",
    gender: false,
    birthdate: new Date(),
    cageId: 0,
    weight: 0,
    isSick: false,
    observations: ""
  };

  gender = "";
  isSick = "";

  constructor(public dialog: MatDialog,
              private animalService: AnimalApiService,
              private snackBar: MatSnackBar,
              private breederService: BreederApiService,
              private route: ActivatedRoute) {
    this.animal.cageId = +this.route.snapshot.paramMap.get('cageid')!;
  }

  openDialog(): void {
    this.dialog.open(DialogComponent);
  }

  handleClick(): void {
    if (!this.animal.name || !this.animal.breed  || !this.animal.cageId ||
      !this.animal.weight || !this.animal.birthdate || !this.isSick || !this.gender) {
      this.openDialog();
    } else {
      // verify if cageId exists for this breeder
      this.breederService.getCagesByBreederId(this.breederService.getBreederId()).subscribe(cages => {
        if (!cages.some(cage => cage.id === this.animal.cageId)) {
          this.snackBar.open('El número de jaula no existe', 'Cerrar', {
            duration: 2000,
          });
        }
        else{
          this.animal.gender = this.gender === "true"; // required to convert the value to a boolean instead of a string
          this.animal.isSick = this.isSick === "true"; // required to convert the value to a boolean instead of a string
          this.registerCuy();
        }
      });
    }
  }

  registerCuy(): void {
    this.animalService.create(this.animal).subscribe(() => {
      this.snackBar.open('Cuy registrado con éxito🤠', 'Cerrar', {
        duration: 2000,
      }).afterDismissed().subscribe(() => {
        window.history.back();
      });
    }, error => {
      this.snackBar.open('Error al registrar cuy😥', 'Cerrar', {
        duration: 2000
      });
    });
  }

  goBack() {
    window.history.back();
  }

}
