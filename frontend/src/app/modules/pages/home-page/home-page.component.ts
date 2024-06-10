import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
  constructor(private router: Router) {}


  selectOption(option: string) {
    if (option === 'skinTypeBudget') {
      this.router.navigate(['/budget-input']);
    } else if (option === 'skinProblemsLifestyle') {
      this.router.navigate(['/forward-chaining-problems']);
    } else if (option === 'specificCriteria') {
      this.router.navigate(['/category-selection']);
    }
  }
}