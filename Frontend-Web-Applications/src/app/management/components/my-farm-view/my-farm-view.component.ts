import { Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Router } from '@angular/router';
import {HeaderComponent} from "../../../public/components/header/header.component";
import {SidenavComponent} from "../../../public/components/sidenav/sidenav.component";

@Component({
  selector: 'app-my-farm-view',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    HeaderComponent,
    SidenavComponent
  ],
  templateUrl: './my-farm-view.component.html',
  styleUrl: './my-farm-view.component.css'
})
export class MyFarmViewComponent {

  constructor(private router: Router) { }
  redirectToManagementResource() {
    this.router.navigate(['criador/mi-granja/recursos']);
  }

  redirectToExpensesResource() {
    this.router.navigate(['criador/mi-granja/gastos']);
  }
}
