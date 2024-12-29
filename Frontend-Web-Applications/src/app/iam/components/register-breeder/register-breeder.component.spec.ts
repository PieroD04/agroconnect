import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterBreederComponent } from './register-breeder.component';

describe('RegisterBreederComponent', () => {
  let component: RegisterBreederComponent;
  let fixture: ComponentFixture<RegisterBreederComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterBreederComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterBreederComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
