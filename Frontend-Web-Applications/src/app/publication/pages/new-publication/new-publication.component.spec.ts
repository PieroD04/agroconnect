import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPublicationComponent } from './new-publication.component';

describe('NewPublicationComponent', () => {
  let component: NewPublicationComponent;
  let fixture: ComponentFixture<NewPublicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewPublicationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewPublicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
