import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { validateRePassword } from './custom-validator/validator';
import { User } from '../model/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent implements OnInit{
  signupForm !: FormGroup;
  hide = true;
  hasError = false;

  allergenFormControl: FormControl[] = [];

  allergensFromDatabase!: any[];

  constructor(private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private authService: AuthService){
    }

  ngOnInit(){
    this.authService.getAllergens().subscribe({
      next: (res) => {
        this.allergensFromDatabase = res;
        
      }
    });

    this.signupForm = this.formBuilder.group({
      username: [
        '',
        [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
        Validators.pattern('[a-zA-Z]+'),

      ],
      ],
      password:['',
          [
            Validators.required,
            Validators.minLength(8),
          ],
      ],
      confirmpsw:['',
          [
            Validators.required,
            validateRePassword,
          ],
      ],
      skinType:['',
        [
          Validators.required,
        ],
      ],
      allergens: [[], [
        Validators.required
      ]]


    });
    
  }

  signup() : void{  
    const user :User = {
      username : (this.signupForm.value as User).username,
      password:(this.signupForm.value as User).password,
      skinType: (this.signupForm.value as User).skinType,
      allergens:(this.signupForm.value as User).allergens,

    }
    console.log(user)
    if (this.signupForm.valid){
      
      
    }else{
      this.hasError = true;
    }


}
}
