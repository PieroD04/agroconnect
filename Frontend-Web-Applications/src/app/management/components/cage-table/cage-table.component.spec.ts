import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CageTableComponent } from './cage-table.component';

describe('CageTableComponent', () => {
  let component: CageTableComponent;
  let fixture: ComponentFixture<CageTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CageTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CageTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
