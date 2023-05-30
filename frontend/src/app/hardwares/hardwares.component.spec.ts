import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HardwaresComponent } from './hardwares.component';

describe('HardwaresComponent', () => {
  let component: HardwaresComponent;
  let fixture: ComponentFixture<HardwaresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HardwaresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HardwaresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
