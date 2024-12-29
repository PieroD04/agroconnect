import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyFarmResourceManagementComponent } from './my-farm-resource-management.component';

describe('MyFarmResourceManagementComponent', () => {
  let component: MyFarmResourceManagementComponent;
  let fixture: ComponentFixture<MyFarmResourceManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyFarmResourceManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyFarmResourceManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
