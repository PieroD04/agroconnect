import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupBreederComponent } from './signup-breeder.component';

describe('SignupBreederComponent', () => {
  let component: SignupBreederComponent;
  let fixture: ComponentFixture<SignupBreederComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SignupBreederComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignupBreederComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
