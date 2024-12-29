import {Component, Input} from '@angular/core';
import {Animal} from "../../models/animal.model";
import {MatCardModule} from "@angular/material/card";
import {ActivatedRoute, Router} from "@angular/router";
import {NgClass} from "@angular/common";

@Component({
  selector: 'animal-card',
  standalone: true,
  imports: [
    MatCardModule,
    NgClass
  ],
  templateUrl: './animal-card.component.html',
  styleUrl: './animal-card.component.css'
})
export class AnimalCardComponent {
  @Input() animal!: Animal;

  constructor(private router: Router,
              private route: ActivatedRoute) {}

  getInformation(id: number){
    this.router.navigate(['informacion', id], {relativeTo: this.route});
  }
}
