import { Component } from '@angular/core';
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatDatepickerModule, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatError, MatFormField, MatHint, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatNativeDateModule} from "@angular/material/core";
import {MatInput} from "@angular/material/input";
import {NgIf} from "@angular/common";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import { DateAdapter, MAT_DATE_LOCALE } from '@angular/material/core';
import { Router } from "@angular/router";
import { MatSnackBar } from "@angular/material/snack-bar";

import { UserApiService } from "../../../user/services/user-api.service";
import { BreederApiService } from "../../../user/services/breeder-api.service";
import { AuthenticationApiService } from "../../services/authentication-api.service";

import { Breeder } from "../../../user/models/breeder.model";


@Component({
  selector: 'register-breeder',
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatDatepickerModule,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatError,
    MatFormField,
    MatHint,
    MatNativeDateModule,
    MatInput,
    MatLabel,
    MatSuffix,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './register-breeder.component.html',
  styleUrl: './register-breeder.component.css',
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'es-PE'}
  ]
})
export class RegisterBreederComponent {
  registerForm: FormGroup = new FormGroup(
    {
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)]),
      location: new FormControl('', [Validators.required]),
      birthDate: new FormControl(null, [Validators.required]),
      description: new FormControl('')
    }
  );

  minDate: Date;
  maxDate: Date;

  constructor(private dateAdapter: DateAdapter<Date>,
              private router: Router,
              private authenticationApiService: AuthenticationApiService,
              private userApiService: UserApiService,
              private breederApiService: BreederApiService,
              private snackBar: MatSnackBar) {
    this.dateAdapter.setLocale('es-PE');
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 90, 0, 1);
    this.maxDate = new Date(currentYear - 18, 11, 31);
  }

  onSubmit() {
    this.authenticationApiService.signUp(this.registerForm.value.email, this.registerForm.value.password, 'ROLE_BREEDER')
      .subscribe((data: any) => {
        // Iniciar sesión automáticamente para obtener el token del usuario
        this.authenticationApiService.signIn(this.registerForm.value.email, this.registerForm.value.password)
          .subscribe((response: any) => {
            let userId = response['id'];
            this.userApiService.setUserId(userId);
            this.userApiService.setLogged(true);

            // Crear un nuevo criador
            const birthDate: Date = this.registerForm.value.birthDate;
            const birthDateString = birthDate.toISOString().split('T')[0];
            let breeder: Breeder = {
              id: 0,
              fullname: this.registerForm.value.name,
              location: this.registerForm.value.location,
              birthdate: birthDateString,
              description: this.registerForm.value.description,
              userId: userId
            };
            this.breederApiService.create(breeder).subscribe(
              (response) => {
                this.userApiService.setIsBreeder(true);
                this.breederApiService.setBreederId(response.id);
                this.router.navigateByUrl('/criador/mi-granja');
                this.snackBar.open('Bievenido ' + breeder.fullname + ' 🤗', 'Cerrar', {
                  duration: 2000
                });
              },
              error => {
                this.snackBar.open('Error al registrar el asesor😥', 'Cerrar', {
                  duration: 5000,
                });
                console.error(error);
              }
            );
          }, error => {
            this.snackBar.open('Error al iniciar sesión😥', 'Cerrar', {
              duration: 3000
            });
          });
      });
  }

  goBack() {
    window.history.back();
  }
}
