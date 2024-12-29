import {Component, Input, ViewChild} from '@angular/core';
import {MatDrawer, MatSidenavModule} from '@angular/material/sidenav';
import {trigger, transition, style, animate} from '@angular/animations';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {NgForOf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {UserApiService} from "../../../user/services/user-api.service";
import {BreederApiService} from "../../../user/services/breeder-api.service";
import {AdvisorApiService} from "../../../user/services/advisor-api.service";

@Component({
  selector: 'app-sidenav',
  standalone: true,
  imports: [
    MatSidenavModule,
    RouterLink,
    RouterLinkActive,
    NgForOf,
    MatIcon,
    MatIconButton
  ],
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({opacity: 0}),
        animate('5ms ease-in-out', style({opacity: 1}))
      ]),
      transition(':leave', [
        animate('5ms ease-in-out', style({opacity: 0}))
      ])
    ])
  ]
})
export class SidenavComponent {
  isOpen = false;
  @ViewChild('drawer') drawer!: MatDrawer;

  onToggleSidenav(isOpen: boolean) {
    isOpen ? this.drawer.open() : this.drawer.close();
  }

  closeDrawer() {
    this.isOpen = false;
  }

  @Input() isBreeder: boolean;

  constructor(private userApiService: UserApiService,
              private breederApiService: BreederApiService,
              private advisorApiService: AdvisorApiService) {
    this.isBreeder = this.userApiService.getIsBreeder();
  }

  getSidebarButtons(): string[] {
    this.isBreeder = this.userApiService.getIsBreeder();
    if (this.isBreeder) {
      return ["Mi granja", "Asesores", "Mis animales", "Publicaciones", "Notificaciones", "Calendario"];
    } else {
      return ["Clientes", "Notificaciones", "Mis publicaciones", "Horarios", "Calendario"];
    }
  }

  getButtonRoute(button: string): string {
    switch (button) {
      case "Mi granja":
        return "criador/mi-granja";
      case "Asesores":
        return "criador/buscar-asesor";
      case "Mis animales":
        return "criador/mis-animales";
      case "Publicaciones":
        return "criador/publicaciones";
      case "Notificaciones":
        return this.isBreeder ? "criador/notificaciones" : "asesor/notificaciones";
      case "Clientes":
        return "asesor/clientes";
      case "Mis publicaciones":
        return "asesor/mis-publicaciones";
      case "Calendario":
        return this.isBreeder ? "criador/calendario" : "asesor/calendario";
      case "Horarios":
        return "asesor/horarios";
      default:
        return "/";
    }
  }

  logOut() {
    this.userApiService.setLogged(false);
    this.userApiService.setUserId(0);
    this.userApiService.clearToken();
    this.breederApiService.setBreederId(0);
    this.advisorApiService.setAdvisorId(0);
    this.onToggleSidenav(false);
    this.isOpen = false;
  }
}
