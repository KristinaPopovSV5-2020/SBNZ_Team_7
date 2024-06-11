import { Component } from '@angular/core';
import { UserGiftReportDTO } from '../../../dto/Gift';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-report-gifts',
  templateUrl: './report-gifts.component.html',
  styleUrl: './report-gifts.component.css'
})
export class ReportGiftsComponent {
  report: UserGiftReportDTO[] = [];

  constructor(
    private reportService: ReportService
  ) {}

  ngOnInit(): void {
    this.getLastMonthGiftsReport();
  }

  getLastMonthGiftsReport(): void {
    this.reportService.getLastMonthGiftsReport().subscribe(report => {
      console.log(report);
      this.report = report;
    });
  }
}
