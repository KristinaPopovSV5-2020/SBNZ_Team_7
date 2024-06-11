import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductDTO, ProductSearchDTO } from '../../../dto/Product';
import { RecommendationService } from '../../../service/recommendation.service';

@Component({
  selector: 'app-category-selection',
  templateUrl: './category-selection.component.html',
  styleUrls: ['./category-selection.component.css']
})
export class CategorySelectionComponent {
  categoryForm: FormGroup;
  products: ProductDTO[] = []; 
  results:boolean = false;
  selectedCategories: ProductSearchDTO | null = null;
  constructor(private fb: FormBuilder, private recommendationService:RecommendationService, private router: Router) {
    this.categoryForm = this.fb.group({
      typeCategory: [''],
      ageCategory: [{ value: '', disabled: true }],
      usageCategory: [{ value: '', disabled: true }]
    });

    this.categoryForm.get('typeCategory')?.valueChanges.subscribe(value => {
      if (value) {
        this.categoryForm.get('ageCategory')?.enable();
      } else {
        this.categoryForm.get('ageCategory')?.reset();
        this.categoryForm.get('ageCategory')?.disable();
        this.categoryForm.get('usageCategory')?.reset();
        this.categoryForm.get('usageCategory')?.disable();
      }
    });

    this.categoryForm.get('ageCategory')?.valueChanges.subscribe(value => {
      if (value) {
        this.categoryForm.get('usageCategory')?.enable();
      } else {
        this.categoryForm.get('usageCategory')?.reset();
        this.categoryForm.get('usageCategory')?.disable();
      }
    });
  }

  onSubmit() {
    this.results = true;
    this.selectedCategories =  this.categoryForm.value;
    this.recommendationService.searchProducts(this.selectedCategories!).subscribe(
      products => {
  
        this.products = products.map(product => ({
          ...product,
          skinTypes: product.skinTypes || [],
          benefits: product.benefits || [],
          ingredientNames: product.ingredientNames || []
        }));
      },
      error => {
        console.error('Error fetching products:', error);
      }
    );
  }
}
