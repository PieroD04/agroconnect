import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCuyComponent } from './register-cuy.component';

describe('RegisterCuyComponent', () => {
  let component: RegisterCuyComponent;
  let fixture: ComponentFixture<RegisterCuyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterCuyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterCuyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
