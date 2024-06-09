import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-budget-input',
  templateUrl: './budget-input.component.html',
  styleUrl: './budget-input.component.css'
})
export class BudgetInputComponent implements OnInit {
  budgetForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    this.budgetForm = this.fb.group({
      minBudget: ['', Validators.required],
      maxBudget: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.budgetForm.valid) {
      const budgetData = this.budgetForm.value;
      localStorage.setItem('budgetData', JSON.stringify(budgetData));
      this.router.navigate(['/recommendation-process']);
    }
  }
}