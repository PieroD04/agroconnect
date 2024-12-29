import { Component, OnInit } from '@angular/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatButton } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { CommonModule } from '@angular/common';
import { HttpClientModule } from "@angular/common/http";
import { MatInputModule } from '@angular/material/input';
import { MatCell, MatHeaderCell } from "@angular/material/table";
import cloneDeep from 'lodash/cloneDeep';
import { FormsModule } from "@angular/forms";
import { EmptyViewComponent } from "../../../public/components/empty-view/empty-view.component";
import { HeaderComponent } from "../../../public/components/header/header.component";
import { SidenavComponent } from "../../../public/components/sidenav/sidenav.component";
import { Router, RouterLink } from "@angular/router";
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from "../../../public/components/confirmation-dialog/confirmation-dialog.component";
import { Observable } from "rxjs";
import { BreederApiService } from "../../../user/services/breeder-api.service";
import { ExpenseApiService } from "../../services/expense-api.service"; // Asumiendo que existe este servicio

@Component({
  selector: 'app-my-farm-expenses-management',
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
    MatIconModule,
    RouterLink,
    MatInputModule,
    MatCell,
    MatHeaderCell
  ],
  templateUrl: './my-farm-expenses-management.component.html',
  styleUrls: ['./my-farm-expenses-management.component.css']
})
export class MyFarmExpensesManagementComponent implements OnInit {
  breeder_id = 0;
  expenses: any[] = [];
  filteredExpenses: any[] = [];

  selectedResourceType: string;
  resourceTypes: { [key: string]: string } = {
    '1': 'Todos',
    '2': 'SALUD',
    '3': 'ALIMENTO',
    '4': 'MANTENIMIENTO DE CRIADERO',
    '5': 'OTROS'
  };

  constructor(private breederApiService: BreederApiService,
              private expenseApiService: ExpenseApiService,
              private router: Router,
              private dialog: MatDialog) {
    this.selectedResourceType = '1';
  }

  ngOnInit(): void {
    this.breeder_id = this.breederApiService.getBreederId();
    console.log("Breeder ID:", this.breeder_id);
    this.loadExpenses();
  }

  filterResource(): void {
    const selectedType = this.selectedResourceType;
    if (selectedType === '1') {
      // Si el tipo seleccionado es 'Todos', mostrar todos los recursos
      this.filteredExpenses = [...this.expenses];
    } else {
      // De lo contrario, filtra los recursos por el tipo seleccionado
      const selectedResourceTypeValue = this.resourceTypes[selectedType];
      this.filteredExpenses = this.expenses.filter(resource => resource.type === selectedResourceTypeValue);
    }
  }

  private loadExpenses() {
    this.breederApiService.getExpenses(this.breeder_id).subscribe((resources) => {
      console.log("Expenses fetched:", resources); // Añade esto para verificar los datos
      this.expenses = resources.filter((resource) => resource.breederId === this.breeder_id);
      this.filteredExpenses = cloneDeep(this.expenses);
      this.filterResource(); // Filtrar recursos después de cargarlos
    });
  }

  editItem(itemId: number): void {
    this.router.navigate(['criador/mi-granja/gastos/editar', itemId]);
  }

  confirmDeletion(id: number): Observable<boolean> {
    // Open a dialog to confirm the deletion
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: `¿Estás seguro de querer eliminar el gasto ${id}? Se eliminará la información que contiene.`
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
        this.expenseApiService.delete(itemId).subscribe(() => {
          console.log("Gasto eliminado con éxito.");
          // Reload the expenses
          this.loadExpenses();
        }, (error) => {
          console.error("Error al eliminar el gasto:", error);
        });
      }
    });
  }

  goBack() {
    this.router.navigate(['/criador/mi-granja']);
  }
}
