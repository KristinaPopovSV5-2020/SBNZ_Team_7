import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule } from '../shared/shared.module';
import { ReportFeedbackPageComponent } from './report-feedback-page/report-feedback-page.component';
import { ReportMainPageComponent } from './report-main-page/report-main-page.component';



@NgModule({
  declarations: [ReportFeedbackPageComponent, ReportMainPageComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule
  ],
  exports:[ReportFeedbackPageComponent, ReportMainPageComponent]
})
export class ReportModule { }
