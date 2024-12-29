import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterExpensesComponent } from './register-expenses.component';

describe('RegisterExpensesComponent', () => {
  let component: RegisterExpensesComponent;
  let fixture: ComponentFixture<RegisterExpensesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterExpensesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterExpensesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
