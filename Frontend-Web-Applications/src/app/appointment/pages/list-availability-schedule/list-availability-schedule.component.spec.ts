import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAvailabilityScheduleComponent } from './list-availability-schedule.component';

describe('ListAvailabilityScheduleComponent', () => {
  let component: ListAvailabilityScheduleComponent;
  let fixture: ComponentFixture<ListAvailabilityScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAvailabilityScheduleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListAvailabilityScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
