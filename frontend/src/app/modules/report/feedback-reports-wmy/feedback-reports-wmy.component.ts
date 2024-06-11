import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ReportService } from '../report.service';
import { FeedbackReportDTO } from '../../../dto/Report';

@Component({
  selector: 'app-feedback-reports-wmy',
  templateUrl: './feedback-reports-wmy.component.html',
  styleUrl: './feedback-reports-wmy.component.css'
})
export class FeedbackReportsWmyComponent implements OnInit {
  wmyReportForm !: FormGroup;

  generate: boolean = false;


  reports: FeedbackReportDTO[] = [];

  
  
  constructor(private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private reportService: ReportService){
  }

  ngOnInit(): void {
    this.wmyReportForm = this.formBuilder.group({
      period:['',
        [
          Validators.required,
        ],
      ],

    });

   
  }

  generateReport(){
    if (this.wmyReportForm.valid){
      this.reportService.getFeedbackReportWMY(this.wmyReportForm.value.period)
      .subscribe({
        next: (response) =>{
          this.generate = true;
          this.reports = response;    
          },
      })
      
      
    }

  }

}


