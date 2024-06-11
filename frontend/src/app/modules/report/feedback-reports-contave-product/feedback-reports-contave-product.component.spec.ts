import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackReportsContaveProductComponent } from './feedback-reports-contave-product.component';

describe('FeedbackReportsContaveProductComponent', () => {
  let component: FeedbackReportsContaveProductComponent;
  let fixture: ComponentFixture<FeedbackReportsContaveProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackReportsContaveProductComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackReportsContaveProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
