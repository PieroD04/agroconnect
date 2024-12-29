import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCageComponent } from './register-cage.component';

describe('RegisterCageComponent', () => {
  let component: RegisterCageComponent;
  let fixture: ComponentFixture<RegisterCageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterCageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterCageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
