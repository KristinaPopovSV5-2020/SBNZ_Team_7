import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ReportService } from '../report.service';
import { UserReportDTO, UserResponseReportDTO } from '../../../dto/User';

@Component({
  selector: 'app-feedback-reports-count',
  templateUrl: './feedback-reports-count.component.html',
  styleUrl: './feedback-reports-count.component.css'
})
export class FeedbackReportsCountComponent implements OnInit {
  feedbackUserForm !: FormGroup;

  generate: boolean = false;


  user!: UserResponseReportDTO;
  users: UserReportDTO[] = [];

  
  constructor(private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private reportService: ReportService){
  }

  ngOnInit(): void {
    this.feedbackUserForm = this.formBuilder.group({
      user:['',
        [
          Validators.required,
        ],
      ],

    });

    this.reportService.getAllUsers()
      .subscribe({
        next: (response) =>{
          for (const value of response) {
            this.users.push({userId: value.userId, username: value.username});
          }
        
          },
        error: (error) => {
          
        }, 

      })
    
    
  }

  generateReport(){
    if (this.feedbackUserForm.valid){
      const pName = this.feedbackUserForm.value.user as string;
      const pr = this.users.find(p => p.username === pName);
      this.reportService.getFeedbackReportPerUser(pr?.userId)
      .subscribe({
        next: (response) =>{
          this.generate = true;
          this.user = response;    
          },
      })

    }

  }

}
