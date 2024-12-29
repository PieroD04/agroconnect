import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMyAdvisorsComponent } from './view-my-advisors.component';

describe('ViewMyAdvisorsComponent', () => {
  let component: ViewMyAdvisorsComponent;
  let fixture: ComponentFixture<ViewMyAdvisorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewMyAdvisorsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewMyAdvisorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
