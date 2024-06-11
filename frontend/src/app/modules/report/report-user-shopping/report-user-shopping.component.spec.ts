import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportUserShoppingComponent } from './report-user-shopping.component';

describe('ReportUserShoppingComponent', () => {
  let component: ReportUserShoppingComponent;
  let fixture: ComponentFixture<ReportUserShoppingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportUserShoppingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportUserShoppingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
