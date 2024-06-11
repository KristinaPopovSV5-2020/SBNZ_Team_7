import { ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs';
import { ProductDTO } from '../../../dto/Product';
import { MatTableDataSource } from '@angular/material/table';
import { FeedbackService } from '../feedback.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-purchases-history',
  templateUrl: './purchases-history.component.html',
  styleUrl: './purchases-history.component.css'
})
export class PurchasesHistoryComponent implements OnInit, OnDestroy{

  @ViewChild(MatPaginator) paginator!: MatPaginator;


  obs!: Observable<any>;


  constructor(private fb: FormBuilder, private dialog: MatDialog,private feedbackService: FeedbackService,private changeDetectorRef: ChangeDetectorRef,private router: Router) {

  }

 


  elements: PurchaseHistory[] = [];

  dataSource: MatTableDataSource<PurchaseHistory> = new MatTableDataSource<PurchaseHistory>(this.elements);


  ngOnInit() {
  this.changeDetectorRef.detectChanges();
  this.dataSource.paginator = this.paginator;
  this.obs = this.dataSource.connect();
    this.feedbackService.getAllUserPurchases()
    .subscribe({
      next: (response) =>{
        for (const value of response) {
          this.elements.push({productId: value.productId, date: value.date,name: value.name, benefits: value.benefits, price: value.price});
        }
        this.dataSource.data = this.elements;
       
        },
      error: (error) => {
        
      }, 

    })

  }

  ngOnDestroy() {
    if (this.dataSource) { 
      this.dataSource.disconnect(); 
    }
  }

  formatEnumString(enumString: string): string {
    return enumString.toLowerCase().replace(/_/g, ' ').replace(/\b\w/g, char => char.toUpperCase());
  }

  leaveFeedback(item: PurchaseHistory){
    this.feedbackService.setSelectedItem(item);
    this.router.navigate(['/leave-feedback']);

  }



}




export interface PurchaseHistory {
  productId: string;
  date: Date;
  name: string;
  benefits: string[];
  price: number;
}





