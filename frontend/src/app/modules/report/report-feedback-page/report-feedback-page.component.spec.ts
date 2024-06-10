import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportFeedbackPageComponent } from './report-feedback-page.component';

describe('ReportFeedbackPageComponent', () => {
  let component: ReportFeedbackPageComponent;
  let fixture: ComponentFixture<ReportFeedbackPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportFeedbackPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportFeedbackPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
