import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportDiscountUsageComponent } from './report-discount-usage.component';

describe('ReportDiscountUsageComponent', () => {
  let component: ReportDiscountUsageComponent;
  let fixture: ComponentFixture<ReportDiscountUsageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportDiscountUsageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportDiscountUsageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
