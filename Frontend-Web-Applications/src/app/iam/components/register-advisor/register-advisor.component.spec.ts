import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAdvisorComponent } from './register-advisor.component';

describe('RegisterAdvisorComponent', () => {
  let component: RegisterAdvisorComponent;
  let fixture: ComponentFixture<RegisterAdvisorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterAdvisorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterAdvisorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
