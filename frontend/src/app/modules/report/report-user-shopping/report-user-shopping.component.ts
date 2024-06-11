import { Component } from '@angular/core';
import { ThresholdValueDTO, UserShoppingReportDTO } from '../../../dto/FeedbackReportThreshold';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from '../../user/user.service';
import { ReportService } from '../report.service';
import { UserReportDTO } from '../../../dto/User';

@Component({
  selector: 'app-report-user-shopping',
  templateUrl: './report-user-shopping.component.html',
  styleUrl: './report-user-shopping.component.css'
})
export class ReportUserShoppingComponent {
  users: UserReportDTO[] = [];
  report: UserShoppingReportDTO | null = null;
  reportForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private reportService: ReportService
  ) {
    this.reportForm = this.fb.group({
      userId: [''],
      value: [0],
      checkDate: [false]
    });
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

  onSubmit(): void {
    const formValue = this.reportForm.value;
    const threshold: ThresholdValueDTO = {
      value: formValue.value,
      checkDate: formValue.checkDate
    };

    this.reportService.getUserShoppingReport(formValue.userId, threshold).subscribe(report => {
      this.report = report;
    });
  }
}
