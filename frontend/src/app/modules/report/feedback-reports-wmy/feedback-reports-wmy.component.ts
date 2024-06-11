import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-feedback-reports-wmy',
  templateUrl: './feedback-reports-wmy.component.html',
  styleUrl: './feedback-reports-wmy.component.css'
})
export class FeedbackReportsWmyComponent implements OnInit {
  wmyReportForm !: FormGroup;

  generate: boolean = false;


  
  
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
      
    }

  }

}


