import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyFarmExpensesManagementComponent } from './my-farm-expenses-management.component';

describe('MyFarmExpensesManagementComponent', () => {
  let component: MyFarmExpensesManagementComponent;
  let fixture: ComponentFixture<MyFarmExpensesManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyFarmExpensesManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyFarmExpensesManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
