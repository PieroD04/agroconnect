import { Component } from '@angular/core';
import { RegisterAdvisorComponent } from "../../components/register-advisor/register-advisor.component";

@Component({
  selector: 'app-signup-advisor',
  standalone: true,
  imports: [
    RegisterAdvisorComponent
  ],
  templateUrl: './signup-advisor.component.html',
  styleUrl: './signup-advisor.component.css'
})
export class SignupAdvisorComponent {

}
