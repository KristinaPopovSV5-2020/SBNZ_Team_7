import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent {

  constructor(private router: Router) {}

  selectOption(option: string) {
    if (option === 'productManagement') {
      this.router.navigate(['/create-product']);
    } else if (option === 'reportManagement') {
      this.router.navigate(['/report-management']);
    }
  }
}
