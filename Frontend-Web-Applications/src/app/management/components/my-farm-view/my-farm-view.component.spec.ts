import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyFarmViewComponent } from './my-farm-view.component';

describe('MyFarmViewComponent', () => {
  let component: MyFarmViewComponent;
  let fixture: ComponentFixture<MyFarmViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyFarmViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyFarmViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
