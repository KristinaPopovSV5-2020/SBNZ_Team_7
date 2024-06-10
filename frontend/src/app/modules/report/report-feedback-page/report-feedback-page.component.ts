import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-report-feedback-page',
  templateUrl: './report-feedback-page.component.html',
  styleUrl: './report-feedback-page.component.css'
})
export class ReportFeedbackPageComponent {


  constructor(private router: Router) {}



  navigateToReport(option: string) {
    if (option === 'weeklyMonthlyYearlyFeedback') {
      this.router.navigate(['/feedback-reports-wmy']);
    } else if (option === 'feedbackCountLast30Days') {
      this.router.navigate(['/feedback-reports-count']);
    }else if (option === 'feedbackCountAndAverageRatingPerProduct') {
      this.router.navigate(['/feedback-reports-countave-product']);
    }else if (option === 'productsAboveRatingThreshold') {
      this.router.navigate(['/feedback-reports-threshold']);
    }
  }

}
