import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-report-main-page',
  templateUrl: './report-main-page.component.html',
  styleUrl: './report-main-page.component.css'
})
export class ReportMainPageComponent {

  constructor(private router: Router) {}

  selectOption(option: string) {
    if (option === 'purchaseReports') {
      this.router.navigate(['/purchase-reports']);
    } else if (option === 'feedbackReports') {
      this.router.navigate(['/feedback-reports']);
    }
  }

}
