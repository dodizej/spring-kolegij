import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HardwareNewComponent } from './hardware-new.component';

describe('HardwaresComponent', () => {
  let component: HardwareNewComponent;
  let fixture: ComponentFixture<HardwareNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HardwareNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HardwareNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
