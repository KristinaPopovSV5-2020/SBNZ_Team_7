import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackReportsWmyComponent } from './feedback-reports-wmy.component';

describe('FeedbackReportsWmyComponent', () => {
  let component: FeedbackReportsWmyComponent;
  let fixture: ComponentFixture<FeedbackReportsWmyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackReportsWmyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackReportsWmyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
