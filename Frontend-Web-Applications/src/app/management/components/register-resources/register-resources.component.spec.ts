import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterResourcesComponent } from './register-resources.component';

describe('RegisterResourcesComponent', () => {
  let component: RegisterResourcesComponent;
  let fixture: ComponentFixture<RegisterResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterResourcesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
