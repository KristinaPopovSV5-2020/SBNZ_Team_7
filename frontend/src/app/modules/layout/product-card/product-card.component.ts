import { Component, Input } from '@angular/core';
import { ProductDTO } from '../../../dto/Product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css'
})
export class ProductCardComponent {
  @Input() product: any;


  buyProduct(product: ProductDTO) {
    // Implementacija logike za kupovinu proizvoda
    // alert(`Product ${product.name} has been added to your cart.`);
  }
}
