import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { DiscountDTO } from '../../../dto/Discount';
import { UserService } from '../../user/user.service';
import { GiftDTO } from '../../../dto/Gift';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-discounts',
  templateUrl: './discounts.component.html',
  styleUrls: ['./discounts.component.css'],
  providers:[DatePipe]
})
export class DiscountsComponent implements OnInit {
  displayedColumns: string[] = ['value', 'dateCreated', 'used'];
  giftDisplayedColumns: string[] = ['giftName', 'timeGiven', 'reason'];
  discountDataSource: MatTableDataSource<DiscountDTO> = new MatTableDataSource();
  giftDataSource: MatTableDataSource<GiftDTO> = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private userService: UserService,  private datePipe: DatePipe) {}

  ngOnInit() {
    this.userService.getUserDiscounts().subscribe(discounts => {
      this.discountDataSource = new MatTableDataSource(discounts);
      this.discountDataSource.paginator = this.paginator;
      this.discountDataSource.sort = this.sort;
    });

    this.userService.getUserGifts().subscribe(gifts => {
      console.log(gifts);
      this.giftDataSource = new MatTableDataSource(gifts);
      this.giftDataSource.paginator = this.paginator;
      this.giftDataSource.sort = this.sort;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.discountDataSource.filter = filterValue.trim().toLowerCase();
    this.giftDataSource.filter = filterValue.trim().toLowerCase();

    if (this.discountDataSource.paginator) {
      this.discountDataSource.paginator.firstPage();
    }

    if (this.giftDataSource.paginator) {
      this.giftDataSource.paginator.firstPage();
    }
  }
  formatDateString(dateString: string): string {
      return `${dateString[2]}.${dateString[1]}.${dateString[0]} ${dateString[3]}:${dateString[4]}:${dateString[5]}`;
    
  }
  
}