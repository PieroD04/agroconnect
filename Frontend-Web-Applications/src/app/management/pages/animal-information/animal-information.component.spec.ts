import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalInformationComponent } from './animal-information.component';

describe('AnimalInformationComponent', () => {
  let component: AnimalInformationComponent;
  let fixture: ComponentFixture<AnimalInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimalInformationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AnimalInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
