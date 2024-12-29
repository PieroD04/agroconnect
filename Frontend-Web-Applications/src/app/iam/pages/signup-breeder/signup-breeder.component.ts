import { Component } from '@angular/core';
import { RegisterBreederComponent } from "../../components/register-breeder/register-breeder.component";

@Component({
  selector: 'app-signup-breeder',
  standalone: true,
  imports: [
    RegisterBreederComponent
  ],
  templateUrl: './signup-breeder.component.html',
  styleUrl: './signup-breeder.component.css'
})
export class SignupBreederComponent {

}
