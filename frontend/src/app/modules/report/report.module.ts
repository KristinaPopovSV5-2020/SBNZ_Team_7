import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule } from '../shared/shared.module';
import { ReportFeedbackPageComponent } from './report-feedback-page/report-feedback-page.component';
import { ReportMainPageComponent } from './report-main-page/report-main-page.component';
import { FeedbackReportsContaveProductComponent } from './feedback-reports-contave-product/feedback-reports-contave-product.component';
import { FeedbackReportsCountComponent } from './feedback-reports-count/feedback-reports-count.component';
import { FeedbackReportsThresholdComponent } from './feedback-reports-threshold/feedback-reports-threshold.component';
import { FeedbackReportsWmyComponent } from './feedback-reports-wmy/feedback-reports-wmy.component';
import { PurchaseReportsComponent } from './purchase-reports/purchase-reports.component';
import { ReportUserShoppingComponent } from './report-user-shopping/report-user-shopping.component';
import { ReportDiscountUsageComponent } from './report-discount-usage/report-discount-usage.component';
import { ReportGiftsComponent } from './report-gifts/report-gifts.component';
import { ReportGiftsNameComponent } from './report-gifts-name/report-gifts-name.component';
import { CardReportFeedbackComponent } from './card-report-feedback/card-report-feedback.component';
import { AllFeedbackComponent } from './all-feedback/all-feedback.component';



@NgModule({
  declarations: [ReportFeedbackPageComponent,  ReportGiftsNameComponent, ReportGiftsComponent,  ReportMainPageComponent,FeedbackReportsContaveProductComponent,FeedbackReportsCountComponent,
    FeedbackReportsThresholdComponent,FeedbackReportsWmyComponent,CardReportFeedbackComponent,ReportDiscountUsageComponent, ReportDiscountUsageComponent,ReportDiscountUsageComponent,PurchaseReportsComponent, ReportUserShoppingComponent, AllFeedbackComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule
  ],
  exports:[ReportFeedbackPageComponent, ReportGiftsComponent, ReportGiftsNameComponent,  ReportDiscountUsageComponent, ReportUserShoppingComponent, ReportMainPageComponent, PurchaseReportsComponent]
})
export class ReportModule { }
