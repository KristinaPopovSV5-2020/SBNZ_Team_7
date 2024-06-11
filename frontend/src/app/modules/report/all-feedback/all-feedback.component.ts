import { ChangeDetectorRef, Component, Input, OnInit, ViewChild } from '@angular/core';
import { FeedbackDTO } from '../../../dto/Report';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-all-feedback',
  templateUrl: './all-feedback.component.html',
  styleUrl: './all-feedback.component.css'
})
export class AllFeedbackComponent  implements OnInit{

  feedbacks: FeedbackDTO[] = [];

  productName: string = "";

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  obs!: Observable<any>;

  dataSource: MatTableDataSource<FeedbackDTO> = new MatTableDataSource<FeedbackDTO>(this.feedbacks);

  constructor(
              public dialog: MatDialog,
              private changeDetectorRef: ChangeDetectorRef,
              private reportService: ReportService,
              private router: Router,) {
                this.feedbacks = this.reportService.feedbacks || [];
                this.productName = this.reportService.productName || "";
               }

  ngOnInit(): void {
    this.changeDetectorRef.detectChanges();

    console.log(this.feedbacks);
    this.dataSource.data = this.feedbacks;
    this.dataSource.paginator = this.paginator;
    this.obs = this.dataSource.connect();
  }



}
