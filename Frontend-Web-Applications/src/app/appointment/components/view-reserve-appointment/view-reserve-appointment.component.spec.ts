import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewReserveAppointmentComponent } from './view-reserve-appointment.component';

describe('ViewReserveAppointmentComponent', () => {
  let component: ViewReserveAppointmentComponent;
  let fixture: ComponentFixture<ViewReserveAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewReserveAppointmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewReserveAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
