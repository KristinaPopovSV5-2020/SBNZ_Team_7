import { Component, Input } from '@angular/core';
import { ProductDTO } from '../../../dto/Product';
import { ProductService } from '../../../service/product.service';
import { UserService } from '../../user/user.service';
import { MatDialog } from '@angular/material/dialog';
import { PurchaseDialogComponent } from '../purchase-dialog/purchase-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css'
})
export class ProductCardComponent {
  @Input() product: any;

  constructor(
    private productService: ProductService,
    private userService: UserService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  buyProduct(product: ProductDTO): void {
    this.userService.getUnusedUserDiscounts().subscribe(discounts => {
      const dialogRef = this.dialog.open(PurchaseDialogComponent, {
        width: '400px',
        data: { product, discounts }
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          console.log(result);
          this.productService.buyProduct(result.shopping.productId, result.selectedDiscountId).subscribe(response => {
            console.log(response);
            let message = 'Purchase successful!';
            if (response.discounts && response.discounts.length > 0) {
              const discountMessages = response.discounts.map((discount: { value: number; }) => 
                `${(discount.value * 100).toFixed(0)}% discount`
              );
              const discountsMessage = discountMessages.join(', ');
              message = `Purchase successful! You received the following discounts: ${discountsMessage}.`;
            }
            if (response.giftDTO) {
            
              message += `You received the following gift: ${response.giftDTO.giftName}. `;
            }
            message += `Your product will arrive in the next 3 days`;

            // Prikazivanje snack bar obaveštenja
            this.snackBar.open(message, 'Close', {
              duration: 5000, // Trajanje obaveštenja u milisekundama
              horizontalPosition: 'center',
              verticalPosition: 'top',
            });

            // Preusmeravanje na drugu stranicu (npr. stranicu sa listom proizvoda)
            this.router.navigate(['/home']);
          });
        }
      });
    });
  }
  
  formatEnumString(enumString: string): string {
    return enumString.toLowerCase().replace(/_/g, ' ').replace(/\b\w/g, char => char.toUpperCase());
  }
}


