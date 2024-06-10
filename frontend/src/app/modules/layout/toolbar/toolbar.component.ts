import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.css'
})
export class ToolbarComponent implements OnInit{
    @Input()
    color!: string;
    role!: string | null;
    constructor(private authService: AuthService,
      private router: Router) {}
  
    ngOnInit(): void {
      this.authService.userState$.subscribe((result) => {
        this.role = result;
      });
    }

    navigateHome() {
      if (this.role == "User") {
        this.router.navigate(['/home']);
      }
      else if (this.role == "Admin"){
        this.router.navigate(['/admin-page'])
      
      } else {
        this.router.navigate(['/home-page']);
      }
    }
  
    
  }


