import { Component } from '@angular/core';
import { DicountUsageReportDTO } from '../../../dto/Discount';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReportService } from '../report.service';
import { UserService } from '../../user/user.service';
import { UserReportDTO } from '../../../dto/User';

@Component({
  selector: 'app-report-discount-usage',
  templateUrl: './report-discount-usage.component.html',
  styleUrl: './report-discount-usage.component.css'
})
export class ReportDiscountUsageComponent {
  report: DicountUsageReportDTO | null = null;
  reportForm: FormGroup;
  users: UserReportDTO[] = [];
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private reportService: ReportService
  ) {
    this.reportForm = this.fb.group({
      userId: ['']
    });
  }
  ngOnInit(): void {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

  onSubmit(): void {
    const userId = this.reportForm.value.userId;
    this.reportService.getDiscountUsageReport(userId).subscribe(report => {
      this.report = report;
    });
  }
}
