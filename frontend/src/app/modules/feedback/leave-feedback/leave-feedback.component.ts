import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { PurchaseHistory, PurchasesHistoryComponent } from '../purchases-history/purchases-history.component';
import { FeedbackService } from '../feedback.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { OkDialogComponent } from '../../shared/ok-dialog/ok-dialog.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-leave-feedback',
  templateUrl: './leave-feedback.component.html',
  styleUrl: './leave-feedback.component.css'
})
export class LeaveFeedbackComponent implements OnInit, OnDestroy {


  product: PurchaseHistory | null = null;

  private subscription: Subscription = new Subscription();

  messageError: string = "";
  hasError = false;
  ratingForm!: FormGroup;


  constructor(private feedbackService: FeedbackService,private formBuilder: FormBuilder,public dialog: MatDialog,){}




  ngOnInit(): void {
    this.subscription = this.feedbackService.selectedItem$.subscribe(
      (item) => {
        this.product = item;
      }
    );
    this.ratingForm = this.formBuilder.group({
      rating:[null,
        Validators.required,
      ],

    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  confirm(){
    const selectedRating = this.ratingForm.get('rating')?.value;
    if (selectedRating !== null) {
      console.log('Selected rating:', selectedRating);
      this.hasError = false;
      this.messageError = '';
      const feedbackVal: FeedbackDTO = {
        productId: this.product?.productId,
        rating: selectedRating,
      };

      this.feedbackService.addFeedback(feedbackVal).subscribe({
        next: (response) =>{
          this.openOKDialog("Successfully left feedback!");
        },
        error: (error) => {
          if (error instanceof HttpErrorResponse){
            console.log(error.error)
            this.hasError = true;
            this.messageError = error.error;
          }
        }, 
  
      })
  
    } else {
      console.log('No rating selected');
      this.hasError = true;
      this.messageError = "Please select a rating";
    }
    
  }

  formatEnumString(enumString: string): string {
    return enumString.toLowerCase().replace(/_/g, ' ').replace(/\b\w/g, char => char.toUpperCase());
  }

  
openOKDialog(message: string) {
  this.dialog.open(OkDialogComponent, {
    data: {dialogMessage: message},
  });
}



  

}


export interface FeedbackDTO{
  productId : string | undefined;
  rating: number;
}
