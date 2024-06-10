import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemsLifestyleInputComponent } from './problems-lifestyle-input.component';

describe('ProblemsLifestyleInputComponent', () => {
  let component: ProblemsLifestyleInputComponent;
  let fixture: ComponentFixture<ProblemsLifestyleInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemsLifestyleInputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemsLifestyleInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
