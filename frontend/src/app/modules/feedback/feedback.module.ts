import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule } from '../shared/shared.module';
import { PurchasesHistoryComponent } from './purchases-history/purchases-history.component';



@NgModule({
  declarations: [PurchasesHistoryComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule,
  ],
  exports:[PurchasesHistoryComponent]
})
export class FeedbackModule { }
