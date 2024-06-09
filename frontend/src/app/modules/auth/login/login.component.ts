import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup,Validators, FormControl} from '@angular/forms';
import { AuthService, LoginDTO } from '../auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  hide = true;
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });
  hasError  = false;

  constructor(private authService: AuthService, private router: Router,
    private dialog: MatDialog) {}

  login(): void {
    const loginVal: LoginDTO = {
      username: (this.loginForm.value as LoginDTO).username,
      password: (this.loginForm.value as LoginDTO).password,
    };

    if (this.loginForm.valid) {
      this.authService.login(loginVal).subscribe({
        next: (result) => {
          localStorage.setItem('user', JSON.stringify(result));
          this.authService.setUser();
          this.router.navigate(['/' + this.authService.getUrlPath()]);
        },
        error: (error) => {
          if (error instanceof HttpErrorResponse) {
            this.hasError = true;
          }
        },
      });
    }
  }

}
