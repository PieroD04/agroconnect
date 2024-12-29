import { TestBed } from '@angular/core/testing';

import { ExpenseApiService } from './expense-api.service';

describe('ExpenseApiService', () => {
  let service: ExpenseApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExpenseApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
