import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupAdvisorComponent } from './signup-advisor.component';

describe('SignupAdvisorComponent', () => {
  let component: SignupAdvisorComponent;
  let fixture: ComponentFixture<SignupAdvisorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SignupAdvisorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignupAdvisorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
