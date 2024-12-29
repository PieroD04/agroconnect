import {Component, Input} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from "@angular/material/card";

@Component({
  selector: 'app-empty-view',
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardActions,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle,
    MatCardContent
  ],
  templateUrl: './empty-view.component.html',
  styleUrl: './empty-view.component.css'
})
export class EmptyViewComponent {
  @Input() title: string;

  constructor() {
    this.title = 'Empty View';
  }
}
