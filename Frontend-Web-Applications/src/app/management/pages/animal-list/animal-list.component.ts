import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {AnimalApiService} from "../../services/animal-api.service";
import {Animal} from "../../models/animal.model";
import {NgForOf, NgIf} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {forEach} from "lodash";
import {AnimalCardComponent} from "../../components/animal-card/animal-card.component";
import {CageApiService} from "../../services/cage-api.service";

@Component({
  selector: 'app-animal-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    MatButton,
    MatIcon,
    AnimalCardComponent,
    RouterLink
  ],
  templateUrl: './animal-list.component.html',
  styleUrl: './animal-list.component.css'
})
export class AnimalListComponent implements OnInit{
  animals: Animal[] = [];

  cageID = -1;
  cageName = '';
  constructor(private router: Router,
              private route: ActivatedRoute,
              private cageService: CageApiService) {
  }

  ngOnInit() {
    this.cageID = +this.route.snapshot.paramMap.get('id')!;
    this.getAnimals();
  }

  getAnimals(){
    this.cageService.getOne(this.cageID).subscribe((res) => {
      this.cageName = res.name;
    });
    this.cageService.getAnimalsByCageId(this.cageID).subscribe((data: any) => {
      this.animals = data;
    });
  }

  createAnimal() {
    this.router.navigate([`/criador/mis-animales/${this.cageID}/registro-animal`]);
  }

  goBack() {
    this.router.navigate([`/criador/mis-animales/`]);
  }
}
