import { Injectable } from '@angular/core';

import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

//Import the expense model
import {Expense} from "../models/expense.model";

//Import the BaseService
import {BaseService} from "../../shared/services/base.service";
@Injectable({
  providedIn: 'root'
})
export class ExpenseApiService extends BaseService<Expense>{
  constructor(http: HttpClient) {
    super(http);
    this.extraUrl = environment.expenseURL;
  }
}
