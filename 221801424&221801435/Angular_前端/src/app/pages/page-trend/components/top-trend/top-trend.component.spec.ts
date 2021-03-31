import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { TopTrendComponent } from './top-trend.component';

describe('TopTrendComponent', () => {
  let component: TopTrendComponent;
  let fixture: ComponentFixture<TopTrendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopTrendComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopTrendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
