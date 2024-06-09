import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-toolbar',
  templateUrl: './admin-toolbar.component.html',
  styleUrl: './admin-toolbar.component.css'
})
export class AdminToolbarComponent {
  color!: string;

  constructor(private authService: AuthService, private router: Router) {}


  logout() {
    localStorage.removeItem('user');
    this.authService.setUser();
    this.router.navigate(['login']);
  }


}
