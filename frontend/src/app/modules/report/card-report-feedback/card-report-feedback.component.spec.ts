import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardReportFeedbackComponent } from './card-report-feedback.component';

describe('CardReportFeedbackComponent', () => {
  let component: CardReportFeedbackComponent;
  let fixture: ComponentFixture<CardReportFeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardReportFeedbackComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardReportFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
