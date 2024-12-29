import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {HeaderComponent} from "./public/components/header/header.component";
import {SidenavComponent} from "./public/components/sidenav/sidenav.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, SidenavComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Frontend-Web-Applications';
}
