import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ReportService } from '../report.service';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs';
import { FeedbackReportThreshold } from '../../../dto/FeedbackReportThreshold';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feedback-reports-threshold',
  templateUrl: './feedback-reports-threshold.component.html',
  styleUrl: './feedback-reports-threshold.component.css'
})
export class FeedbackReportsThresholdComponent implements OnInit{
  thresholdForm !: FormGroup;

  generate: boolean = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;


  obs!: Observable<any>;


  elements: FeedbackReportThreshold[] = [];

  dataSource: MatTableDataSource<FeedbackReportThreshold> = new MatTableDataSource<FeedbackReportThreshold>(this.elements);


  

  constructor(private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private reportService: ReportService,private changeDetectorRef: ChangeDetectorRef,private router: Router){
    }

  ngOnInit(): void {
    this.thresholdForm = this.formBuilder.group({
      threshold:['',
        [
          Validators.required,
        ],
      ],

    });
    
  }

  generateReport(){
    if(this.thresholdForm.valid){
      this.changeDetectorRef.detectChanges();
      this.dataSource.paginator = this.paginator;
      this.obs = this.dataSource.connect();
        this.reportService.getFeedbackReportsThreshold(this.thresholdForm.value.threshold)
        .subscribe({
          next: (response) =>{
            this.elements = [];
            for (const value of response) {
              this.elements.push({productId: value.productId, name: value.name,numberOfProducts: value.numberOfProducts, average: value.average});
            }
            this.dataSource.data = this.elements;
            this.generate = true;
          
            },
          error: (error) => {
            
          }, 

        })
    }

  }

}
