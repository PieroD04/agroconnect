import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationsViewComponent } from './publications-view.component';

describe('PublicationsViewComponent', () => {
  let component: PublicationsViewComponent;
  let fixture: ComponentFixture<PublicationsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicationsViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublicationsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
