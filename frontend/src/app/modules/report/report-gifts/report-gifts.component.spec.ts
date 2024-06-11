import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportGiftsComponent } from './report-gifts.component';

describe('ReportGiftsComponent', () => {
  let component: ReportGiftsComponent;
  let fixture: ComponentFixture<ReportGiftsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportGiftsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportGiftsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
