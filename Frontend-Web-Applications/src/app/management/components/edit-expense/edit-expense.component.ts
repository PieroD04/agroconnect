import {Component, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {FormsModule} from '@angular/forms';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {ReactiveFormsModule} from "@angular/forms";
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import { ExpenseApiService } from '../../services/expense-api.service';
import { Location } from '@angular/common';
import {MatIcon} from "@angular/material/icon";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-edit-expense',
  standalone: true,
    imports: [
        MatButtonModule,
        MatFormField,
        MatInput,
        MatLabel,
        MatRadioButton,
        MatRadioGroup,
        ReactiveFormsModule,
        FormsModule,
        CommonModule,
        RouterLink,
        MatIcon
    ],
  templateUrl: './edit-expense.component.html',
  styleUrl: './edit-expense.component.css'
})
export class EditExpenseComponent implements OnInit{
  expense: any = {
    name: '',
    amount: '',
    type: '',
    observations: '',
    date: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private expenseApiService: ExpenseApiService,
    private location: Location,
    private snackBar: MatSnackBar
  ) { }
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadExpense(id);
    }
  }

  private loadExpense(id: string) {
    this.expenseApiService.getOne(id).subscribe((expense: any) => {
      this.expense = expense;
    }, error => {
      console.error('Error loading expense', error);
    });
  }

  handleClick(): void {
    this.expenseApiService.update(this.expense.id, this.expense).subscribe(() => {
      this.snackBar.open('Gasto actualizado con éxito🤗', 'Cerrar', {
        duration: 2000
      }).afterDismissed().subscribe(() => {
        this.router.navigate(['/criador/mi-granja/gastos']);
      });

    }, error => {
      this.snackBar.open('Error al actualizar el gasto😪', 'Cerrar', {
        duration: 2000
      });
    });
  }

  goBack(): void {
    this.location.back();
  }
}
