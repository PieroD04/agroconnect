import { Component, EventEmitter, Output } from '@angular/core';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {UserApiService} from "../../../user/services/user-api.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatIconButton,
    MatIcon,
    NgIf
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isOpen = false;

  @Output() toggleSidenav = new EventEmitter<boolean>();

  constructor(private userApiService: UserApiService) {
  }

  toggleDrawer() {
    this.isOpen = !this.isOpen;
    this.toggleSidenav.emit(this.isOpen);
  }

  isLogged(){
    return this.userApiService.isLogged();
  }
}
