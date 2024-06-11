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
    }else if (option === 'gifts-name') {
      this.router.navigate(['/gifts-name']);
    }else if (option === 'gifts') {
      this.router.navigate(['/gifts']);
    }
  }
}
