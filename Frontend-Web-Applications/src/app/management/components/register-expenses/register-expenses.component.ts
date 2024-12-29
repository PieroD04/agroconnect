import {Component, OnInit} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {RouterLink} from "@angular/router";
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {CommonModule} from "@angular/common";
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../../../public/components/dialog/dialog.component';
import { Expense } from '../../models/expense.model';
import { ExpenseApiService } from "../../services/expense-api.service";
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {BreederApiService} from "../../../user/services/breeder-api.service";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-register-expenses',
  standalone: true,
    imports: [
        MatButton,
        RouterLink,
        MatInputModule,
        MatFormFieldModule,
        FormsModule,
        CommonModule,
        MatRadioButton,
        MatRadioGroup,
        MatIcon
    ],
  templateUrl: './register-expenses.component.html',
  styleUrl: './register-expenses.component.css'
})
export class RegisterExpensesComponent implements OnInit {
  expense: Expense = {
    name: "",
    type: "ALIMENTO",
    amount: 0,
    date: "",
    observations: "Ninguna",
    breederId: 0
  };

  constructor(public dialog: MatDialog,
              private expenseService: ExpenseApiService,
              private breederService: BreederApiService,
              private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.expense.breederId = this.breederService.getBreederId();
  }

  openDialog(): void {
    this.dialog.open(DialogComponent);
  }

  handleClick(): void {
    if (!this.expense.amount || !this.expense.type || !this.expense.date) {
      this.openDialog();
    } else {
      this.registerExpense();
    }
  }

  registerExpense(): void {
    this.expenseService.create(this.expense).subscribe(() => {
      this.snackBar.open('Gasto registrado con éxito🥳', 'Cerrar', {
        duration: 2000,
      }).afterDismissed().subscribe(() => {
        window.history.back();
      });
    }, error => {
      console.error(error);
      this.snackBar.open('Error al registrar el gasto😥', 'Cerrar', {
        duration: 2000,
      });
    });
  }

  goBack() {
    window.history.back();
  }
}
