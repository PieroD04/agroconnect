import { Component, OnInit } from '@angular/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatButton } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { CommonModule } from '@angular/common';
import { HttpClientModule } from "@angular/common/http";

// library lodash to clone objects
import * as _ from 'lodash';
import cloneDeep from 'lodash/cloneDeep';

import { FormsModule } from "@angular/forms";
import {EmptyViewComponent} from "../../../public/components/empty-view/empty-view.component";
import {HeaderComponent} from "../../../public/components/header/header.component";
import {SidenavComponent} from "../../../public/components/sidenav/sidenav.component";
import {MatIcon} from "@angular/material/icon";
import {Router, RouterLink} from "@angular/router";
import {ResourceApiService} from "../../services/resource-api.service";

// Import the ConfirmationDialogComponent
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from "../../../public/components/confirmation-dialog/confirmation-dialog.component";
import { Observable } from "rxjs";
import {BreederApiService} from "../../../user/services/breeder-api.service";

@Component({
  selector: 'app-my-farm-resource-management',
  standalone: true,
  imports: [
    MatRadioModule,
    MatButton,
    MatCardModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    EmptyViewComponent,
    HeaderComponent,
    SidenavComponent,
    MatIcon,
    RouterLink
  ],
  templateUrl: './my-farm-resource-management.component.html',
  styleUrl: './my-farm-resource-management.component.css'
})
export class MyFarmResourceManagementComponent implements OnInit {
  breederId: number = 0;
  resources: any[] = [];
  filteredResources: any[] = [];

  selectedResourceType: string;
  resourceTypes: { [key: string]: string } = {
    '1': 'OTROS',
    '2': 'ALIMENTO',
    '3': 'MEDICINA',
    '4': 'CULTIVO'
  };

  constructor(private resourceApiService: ResourceApiService,
              private router: Router,
              private dialog: MatDialog,
              private breederService: BreederApiService) {
    this.selectedResourceType = '1';
  }

  ngOnInit(): void {
    this.breederId = this.breederService.getBreederId();
    this.loadResources();
  }

  filterResource(): void {
    const selectedType = this.selectedResourceType;
    if (selectedType === '1') {
      // Si el tipo seleccionado es 'Todos', mostrar todos los recursos
      this.filteredResources = [...this.resources];
    } else {
      // De lo contrario, filtra los recursos por el tipo seleccionado
      this.filteredResources = this.resources.filter(resource => resource.type === this.resourceTypes[selectedType]);
    }
  }

  private loadResources() {
    this.breederService.getResources(this.breederId).subscribe((resources: any) => {
      this.resources = resources;
      this.filteredResources = cloneDeep(this.resources);
    });
  }


  goBack() {
    this.router.navigate(['/criador/mi-granja']);
  }

  editItem(itemId: number) {
    this.router.navigate(['criador/mi-granja/recursos/editar', itemId]);
  }

  confirmDeletion(id: number): Observable<boolean> {
    // Open a dialog to confirm the deletion
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: `¿Estás seguro de querer eliminar el recurso ${id}? Se eliminará la información que contiene.`
      }
    });
    // Return the result of the dialog
    return dialogRef.afterClosed();
  }

  deleteItem(itemId: number) {
    // First, confirm the deletion
    this.confirmDeletion(itemId).subscribe(confirmado => {
      if (confirmado) {
        // If the deletion is confirmed, delete the item
        this.resourceApiService.delete(itemId).subscribe(() => {
          console.log("Recurso eliminado con éxito.");
          // Reload the expenses
          this.loadResources();
        }, (error) => {
          console.error("Error al eliminar el recurso:", error);
        });
      }
    });
  }
}
