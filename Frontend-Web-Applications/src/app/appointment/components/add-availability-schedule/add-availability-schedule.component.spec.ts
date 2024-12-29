import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAvailabilityScheduleComponent } from './add-availability-schedule.component';

describe('AddAvailabilityScheduleComponent', () => {
  let component: AddAvailabilityScheduleComponent;
  let fixture: ComponentFixture<AddAvailabilityScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAvailabilityScheduleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddAvailabilityScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
