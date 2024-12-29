import {Component, Input} from '@angular/core';
import {Publication} from "../../models/publication.model";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {DatePipe} from "@angular/common";
import {Router} from "@angular/router";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'publication-readonly-card',
  standalone: true,
    imports: [
        MatButtonModule,
        MatCardModule,
        DatePipe,
        MatIcon
    ],
  templateUrl: './publication-readonly-card.component.html',
  styleUrl: './publication-readonly-card.component.css'
})
export class PublicationReadonlyCardComponent {
  @Input() publication !: Publication;

  constructor(private router: Router) { }

  goToAdvisor() {
    this.router.navigate(['/criador/asesor-info/' + this.publication.advisorId]);
  }
}
