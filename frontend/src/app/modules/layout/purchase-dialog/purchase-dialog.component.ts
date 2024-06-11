import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductDTO } from '../../../dto/Product';
import { ProductService } from '../../../service/product.service';
import { DiscountDTO } from '../../../dto/Discount';

@Component({
  selector: 'app-purchase-dialog',
  templateUrl: './purchase-dialog.component.html',
  styleUrl: './purchase-dialog.component.css'
})

export class PurchaseDialogComponent implements OnInit {
  discounts: DiscountDTO[] = [];
  selectedDiscountId: string | null = null;
  productName: string;
  price:string = '';
  constructor(
    public dialogRef: MatDialogRef<PurchaseDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { product: ProductDTO, discounts: DiscountDTO[], productName: string },
    private productService: ProductService
  ) {
    this.productName = data.product.name;
    this.price = data.product.price + ' din';
  }

  ngOnInit(): void {
    this.discounts = this.data.discounts;
  }

  confirmPurchase(): void {
    this.dialogRef.close({
      shopping: { productId: this.data.product.id },
      selectedDiscountId: this.selectedDiscountId
    });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }


  formatDiscountValue(value: number): string {
    return `${(value * 100).toFixed(0)}%`;
  }
}