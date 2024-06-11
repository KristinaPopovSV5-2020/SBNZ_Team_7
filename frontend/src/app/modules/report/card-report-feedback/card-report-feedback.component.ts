import { Component, Input } from '@angular/core';
import { FeedbackDTO, FeedbackReportDTO } from '../../../dto/Report';
import {  Router } from '@angular/router';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-card-report-feedback',
  templateUrl: './card-report-feedback.component.html',
  styleUrl: './card-report-feedback.component.css'
})
export class CardReportFeedbackComponent {
  @Input() report: FeedbackReportDTO | undefined;


  constructor(private router: Router, private reportService: ReportService){}



  view(feedbacks: FeedbackDTO[] | undefined, productName: string |undefined){
    console.log(feedbacks);
    this.reportService.feedbacks = feedbacks;
    this.reportService.productName = productName;
    this.router.navigate(['/feedbacks']);

  }

}
