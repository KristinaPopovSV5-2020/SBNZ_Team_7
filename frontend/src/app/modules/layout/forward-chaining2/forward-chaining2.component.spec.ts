import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForwardChaining2Component } from './forward-chaining2.component';

describe('ForwardChaining2Component', () => {
  let component: ForwardChaining2Component;
  let fixture: ComponentFixture<ForwardChaining2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForwardChaining2Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForwardChaining2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
