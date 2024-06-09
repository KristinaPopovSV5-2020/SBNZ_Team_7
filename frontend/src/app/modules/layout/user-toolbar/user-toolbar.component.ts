import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-toolbar',
  templateUrl: './user-toolbar.component.html',
  styleUrl: './user-toolbar.component.css'
})
export class UserToolbarComponent {

  color!: string;
  constructor(private authService: AuthService, private router: Router) {
    console.log(authService.getRole())
  }


  logout() {
    localStorage.removeItem('user');
    this.authService.setUser();
    this.router.navigate(['home-page']);
  }

}
