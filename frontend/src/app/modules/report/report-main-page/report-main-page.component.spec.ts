import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportMainPageComponent } from './report-main-page.component';

describe('ReportMainPageComponent', () => {
  let component: ReportMainPageComponent;
  let fixture: ComponentFixture<ReportMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportMainPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
