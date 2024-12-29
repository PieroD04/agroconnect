import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationReadonlyCardComponent } from './publication-readonly-card.component';

describe('PublicationReadonlyCardComponent', () => {
  let component: PublicationReadonlyCardComponent;
  let fixture: ComponentFixture<PublicationReadonlyCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicationReadonlyCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublicationReadonlyCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
