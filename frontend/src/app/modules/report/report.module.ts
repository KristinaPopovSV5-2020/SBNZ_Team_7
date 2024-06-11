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
import { CardReportFeedbackComponent } from './card-report-feedback/card-report-feedback.component';



@NgModule({
  declarations: [ReportFeedbackPageComponent, ReportMainPageComponent,FeedbackReportsContaveProductComponent,FeedbackReportsCountComponent,
    FeedbackReportsThresholdComponent,FeedbackReportsWmyComponent,CardReportFeedbackComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule
  ],
  exports:[ReportFeedbackPageComponent, ReportMainPageComponent,CardReportFeedbackComponent]
})
export class ReportModule { }
