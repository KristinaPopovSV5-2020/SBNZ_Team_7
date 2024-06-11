import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackReportsCountComponent } from './feedback-reports-count.component';

describe('FeedbackReportsCountComponent', () => {
  let component: FeedbackReportsCountComponent;
  let fixture: ComponentFixture<FeedbackReportsCountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackReportsCountComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackReportsCountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
