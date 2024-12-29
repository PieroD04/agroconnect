import { Component } from '@angular/core';
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatButtonModule } from "@angular/material/button";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'register-user',
  standalone: true,
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatButtonModule,
    RouterLink
  ],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css'
})
export class RegisterUserComponent {

  constructor(private router: Router) {
  }

  registerBreeder() {
    this.router.navigate(['/registro/criador']);
  }

  registerAdvisor() {
    this.router.navigate(['/registro/asesor']);
  }

}
