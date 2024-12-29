import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAdvisorsSearchComponent } from './view-advisors-search.component';

describe('ViewAdvisorsSearchComponent', () => {
  let component: ViewAdvisorsSearchComponent;
  let fixture: ComponentFixture<ViewAdvisorsSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAdvisorsSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAdvisorsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
