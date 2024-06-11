import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-purchase-reports',
  templateUrl: './purchase-reports.component.html',
  styleUrl: './purchase-reports.component.css'
})
export class PurchaseReportsComponent {

  constructor(private router: Router) {}


  
  navigateToReport(option: string) {
    if (option === 'user-shopping-report') {
      this.router.navigate(['/user-shopping-report']);
    } else if (option === 'discount-usage') {
      this.router.navigate(['/discount-usage']);
    }else if (option === 'feedbackCountAndAverageRatingPerProduct') {
      this.router.navigate(['/feedback-reports-countave-product']);
    }else if (option === 'productsAboveRatingThreshold') {
      this.router.navigate(['/feedback-reports-threshold']);
    }
  }
}
