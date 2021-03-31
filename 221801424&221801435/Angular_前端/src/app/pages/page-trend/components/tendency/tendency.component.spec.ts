import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TendencyComponent } from './tendency.component';

describe('TendencyComponent', () => {
  let component: TendencyComponent;
  let fixture: ComponentFixture<TendencyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TendencyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TendencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
