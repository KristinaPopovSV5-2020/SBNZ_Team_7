import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule } from '../shared/shared.module';
import { PurchasesHistoryComponent } from './purchases-history/purchases-history.component';
import { LeaveFeedbackComponent } from './leave-feedback/leave-feedback.component';



@NgModule({
  declarations: [PurchasesHistoryComponent, LeaveFeedbackComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule,
  ],
  exports:[PurchasesHistoryComponent, LeaveFeedbackComponent]
})
export class FeedbackModule { }
