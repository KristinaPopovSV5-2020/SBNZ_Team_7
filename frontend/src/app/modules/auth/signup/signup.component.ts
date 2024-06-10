import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { validateRePassword } from './custom-validator/validator';
import { User } from '../model/user';
import { OkDialogComponent } from '../../shared/ok-dialog/ok-dialog.component';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent implements OnInit{
  signupForm !: FormGroup;
  hide = true;
  hasError = false;
  selectedAllergens: string[] = [];

  hasErrorUser = false;

  allergensFromDatabase!: any[];

  constructor(private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private authService: AuthService,){
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

    });
    
  }

  signup() : void{ 
    if (this.signupForm.valid){
      const user :User = {
        username : (this.signupForm.value as User).username,
        password:(this.signupForm.value as User).password,
        skinType: (this.signupForm.value as User).skinType,
        allergens:this.selectedAllergens,
  
      }
      console.log(user)
      this.authService.register(user).subscribe({
        next: (user) =>{
          this.openOKDialog("Successfully registered");
        },
        error: (error) => {
          if (error instanceof HttpErrorResponse){
            console.log(error.error)
            this.hasErrorUser = true;
          }
        }, 
  
      })
      
      
    }else{
      this.hasError = true;
    }


}

onAllergenChange(event: any, allergen: any) {
  if (event.checked) {
    this.selectedAllergens.push(allergen.name);
  } else {
    const index = this.selectedAllergens.indexOf(allergen.name);
    if (index > -1) {
      this.selectedAllergens.splice(index, 1);
    }
  }
}

openOKDialog(message: string) {
  this.dialog.open(OkDialogComponent, {
    data: {dialogMessage: message},
  });
}

}
