import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportGiftsNameComponent } from './report-gifts-name.component';

describe('ReportGiftsNameComponent', () => {
  let component: ReportGiftsNameComponent;
  let fixture: ComponentFixture<ReportGiftsNameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportGiftsNameComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportGiftsNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
