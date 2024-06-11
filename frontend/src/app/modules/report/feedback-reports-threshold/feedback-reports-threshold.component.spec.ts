import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackReportsThresholdComponent } from './feedback-reports-threshold.component';

describe('FeedbackReportsThresholdComponent', () => {
  let component: FeedbackReportsThresholdComponent;
  let fixture: ComponentFixture<FeedbackReportsThresholdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackReportsThresholdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackReportsThresholdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
