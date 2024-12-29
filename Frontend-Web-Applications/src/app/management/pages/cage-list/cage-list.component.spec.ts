import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CageListComponent } from './cage-list.component';

describe('FarmComponent', () => {
  let component: CageListComponent;
  let fixture: ComponentFixture<CageListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CageListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CageListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
