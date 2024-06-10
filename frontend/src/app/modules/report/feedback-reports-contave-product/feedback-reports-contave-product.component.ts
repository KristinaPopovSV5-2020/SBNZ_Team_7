import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ReportService } from '../report.service';
import { ProductFeedbackDTO } from '../../../dto/Product';
import { FeedbackReportThreshold } from '../../../dto/FeedbackReportThreshold';

@Component({
  selector: 'app-feedback-reports-contave-product',
  templateUrl: './feedback-reports-contave-product.component.html',
  styleUrl: './feedback-reports-contave-product.component.css'
})
export class FeedbackReportsContaveProductComponent implements OnInit {
  caProductForm !: FormGroup;

  generate: boolean = false;


  product!: FeedbackReportThreshold;
  products: ProductFeedbackDTO[] = [];

  
  constructor(private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private reportService: ReportService){
    }

  ngOnInit(): void {
    this.caProductForm = this.formBuilder.group({
      product:['',
        [
          Validators.required,
        ],
      ],

    });

    this.reportService.getAllProducts()
      .subscribe({
        next: (response) =>{
          for (const value of response) {
            this.products.push({productId: value.productId, name: value.name});
          }
        
          },
        error: (error) => {
          
        }, 

      })
    
    
  }

  generateReport(){
    if (this.caProductForm.valid){
      const pName = this.caProductForm.value.product as string;
      const pr = this.products.find(p => p.name === pName);
      this.reportService.getFeedbackReportCAProduct(pr?.productId)
      .subscribe({
        next: (response) =>{
          this.generate = true;
          this.product = response;    
          },
      })

    }

  }

}
