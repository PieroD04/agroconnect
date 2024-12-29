import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAdvisorAboutusComponent } from './view-advisor-aboutus.component';

describe('ViewAdvisorAboutusComponent', () => {
  let component: ViewAdvisorAboutusComponent;
  let fixture: ComponentFixture<ViewAdvisorAboutusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAdvisorAboutusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAdvisorAboutusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
