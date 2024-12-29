import {Component, OnInit, ViewChild} from '@angular/core';
import {UserApiService} from "../../../user/services/user-api.service";
import {Router, RouterLink} from "@angular/router";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {User} from "../../../user/models/user.model";
import {NgIf} from "@angular/common";
import {BreederApiService} from "../../../user/services/breeder-api.service";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthenticationApiService} from "../../services/authentication-api.service";
import {MatDrawer} from "@angular/material/sidenav";

@Component({
  selector: 'app-login',
  standalone: true,
    imports: [
      MatCardModule,
      MatFormFieldModule,
      MatInput,
      ReactiveFormsModule,
      MatButton,
      RouterLink,
      NgIf
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  user = new User();
  errorMessage: string | null = null;
  loginAttempts: number = 0;

  constructor(private userApiService: UserApiService,
              private authenticationApiService: AuthenticationApiService,
              private breederApiService: BreederApiService,
              private advisorApiService: AdvisorApiService,
              private router: Router,
              private formBuilder: FormBuilder,
              private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email] ],
      password: ['', Validators.required ]
    });

    if (this.userApiService.isLogged()) {
      if (this.userApiService.getIsBreeder()) {
        this.router.navigateByUrl('/criador/mi-granja');
      } else {
        this.router.navigateByUrl('/asesor/clientes');
      }
    }
  }

  login() {
    if (this.loginAttempts > 3) {
      this.errorMessage = "Has alcanzado el límite de intentos de inicio de sesión. Por favor, inténtalo más tarde.";
      return;
    }

    this.loginAttempts++;

    this.authenticationApiService.signIn(this.loginForm.value.email, this.loginForm.value.password).subscribe(
      (response: any) => {
        let userId = response['id'];
        this.userApiService.setUserId(userId);
        this.userApiService.setLogged(true);
        this.breederApiService.getAll().subscribe((data) => {
          const breeder = data.find(breeder => breeder.userId === userId);
          if (breeder) {
            this.userApiService.setIsBreeder(true);
            this.breederApiService.setBreederId(breeder.id);
            this.router.navigateByUrl('/criador/mi-granja');
            this.snackBar.open('Bievenido ' + breeder.fullname + ' 🤗', 'Cerrar', {
              duration: 2000
            });
          } else {
            this.advisorApiService.getAll().subscribe((data) => {
              const advisor = data.find(advisor => advisor.userId === userId);
              if (advisor) {
                this.userApiService.setIsBreeder(false);
                this.advisorApiService.setAdvisorId(advisor.id);
                this.router.navigateByUrl('/asesor/clientes');
                this.snackBar.open('Bievenido ' + advisor.fullname + ' 🤗', 'Cerrar', {
                  duration: 2000
                });
              }
            });
          }

        });

      }, error => {
        this.snackBar.open('Error. Credenciales no encontradas😥', 'Cerrar', {
          duration: 3000
        });
      }
    );
  }
}
