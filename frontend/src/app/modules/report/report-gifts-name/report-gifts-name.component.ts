import { Component } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserGiftReportDTO, GiftNameDTO } from '../../../dto/Gift';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-report-gifts-name',
  templateUrl: './report-gifts-name.component.html',
  styleUrl: './report-gifts-name.component.css'
})
export class ReportGiftsNameComponent {
  report: UserGiftReportDTO[] = [];
  reportForm: FormGroup;
  giftOptions: string[] = [
    'Sunscreen Tester',
    'Hydration Serum Tester',
    'Anti-Ageing Cream Tester',
    'Exfoliating Scrub Tester',
    'Brightening Mask Tester'
  ];
  constructor(
    private fb: FormBuilder,
    private reportService: ReportService
  ) {
    this.reportForm = this.fb.group({
      giftName: ['']
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    const giftNameDTO: GiftNameDTO = {
      giftName: this.reportForm.value.giftName
    };
    this.reportService.getGiftsReport(giftNameDTO).subscribe(report => {
      this.report = report;
      console.log(report);
    });
  }
}
