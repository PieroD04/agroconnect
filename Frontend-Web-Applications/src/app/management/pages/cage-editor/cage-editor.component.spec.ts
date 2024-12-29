import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CageEditorComponent } from './cage-editor.component';

describe('CageEditorComponent', () => {
  let component: CageEditorComponent;
  let fixture: ComponentFixture<CageEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CageEditorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CageEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
