import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IngredientDTO } from '../../../dto/Ingredient';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { ProductService } from '../../../service/product.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.css'
})
export class CreateProductComponent {
  productForm: FormGroup;
  skinTypes = ['OILY', 'DRY', 'COMBINATION', 'NORMAL'];
  private apiUrl = `${environment.apiHost}api/`;
  isLoading = false;
  benefits = [
    'MOISTURIZING',
    'ANTI_AGE',
    'HYDRATION',
    'DARK_SPOT_REMOVAL',
    'HYPERPIGMENTATION_REDUCTION',
    'SUN_PROTECTION',
    'ACNE_REDUCING',
    'SKIN_TEXTURE_IMPROVEMENT',
    'IRRITATION_CALMING',
    'DETOXIFICATION',
    'CLEANSING',
    'SKIN_TONE_EVENING'
  ];
  typeCategories = ['Creams', 'Lotions', 'Serums'];
  ageCategories: string[] = [];
  usageCategories: string[] = [];
  ingredients: IngredientDTO[] = [];

  constructor(private fb: FormBuilder,  private http: HttpClient,   private router: Router,private productService: ProductService, private snackBar: MatSnackBar) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      instruction: ['', Validators.required],
      vegan: [false],
      skinTypes: ['', Validators.required],
      benefits: ['', Validators.required],
      ingredientIds: ['', Validators.required],
      productSearchDTO: this.fb.group({
        typeCategory: ['', Validators.required],
        ageCategory: [{value: '', disabled: true}],
        usageCategory: [{value: '', disabled: true}]
      })
    });
  }

  ngOnInit(): void {
    this.productService.getIngredients().subscribe(data => {
      this.ingredients = data;
    });
    this.productForm.get('productSearchDTO.typeCategory')?.valueChanges.subscribe(value => {
      if (value) {
        this.ageCategories = ['Kids', 'Elderly', 'Youngsters'];
        this.productForm.get('productSearchDTO.ageCategory')?.enable();
      } else {
        this.ageCategories = [];
        this.productForm.get('productSearchDTO.ageCategory')?.disable();
      }
      this.productForm.get('productSearchDTO.ageCategory')?.setValue('');
      this.productForm.get('productSearchDTO.usageCategory')?.setValue('');
      this.productForm.get('productSearchDTO.usageCategory')?.disable();
    });

    this.productForm.get('productSearchDTO.ageCategory')?.valueChanges.subscribe(value => {
      if (value) {
        this.usageCategories = ['day', 'night', 'day&night'];
        this.productForm.get('productSearchDTO.usageCategory')?.enable();
      } else {
        this.usageCategories = [];
        this.productForm.get('productSearchDTO.usageCategory')?.disable();
      }
      this.productForm.get('productSearchDTO.usageCategory')?.setValue('');
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      this.isLoading = true;
      this.productService.addProduct(this.productForm.value).subscribe({
        next: (response) => {
          this.isLoading = false;
          this.snackBar.open('Product created successfully!', 'Close', {
            duration: 3000
          });
          this.router.navigate(['/admin-page']);
        },
        error: (error) => {
          this.isLoading = false;
          this.snackBar.open('Failed to create product. Please try again.', 'Close', {
            duration: 3000
          });
        }
      });
    }
  }
}