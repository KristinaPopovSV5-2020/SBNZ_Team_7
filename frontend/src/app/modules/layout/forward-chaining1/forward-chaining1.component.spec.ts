import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForwardChaining1Component } from './forward-chaining1.component';

describe('ForwardChaining1Component', () => {
  let component: ForwardChaining1Component;
  let fixture: ComponentFixture<ForwardChaining1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForwardChaining1Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForwardChaining1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
