import { Component, OnInit } from '@angular/core';
import { ProductDTO } from '../../../dto/Product';
import { RecommendationService } from '../../../service/recommendation.service';
import { BudgetDTO } from '../../../dto/Budget';

@Component({
  selector: 'app-forward-chaining1',
  templateUrl: './forward-chaining1.component.html',
  styleUrl: './forward-chaining1.component.css'
})
export class ForwardChaining1Component implements OnInit {
  loading = true;
  loadingMessage = 'Getting ready to make most suitable recommendations...';
  recommendations: ProductDTO[] = [];
  errorMessage: string | null = null;
  budget: BudgetDTO | null = null;

  constructor(private recommendationService:RecommendationService){}
  ngOnInit() {
    const userId = '6651dc2b4758c54a8ec3796e';
    const budgetData = localStorage.getItem('budgetData');
    if (budgetData) {
      this.budget = JSON.parse(budgetData);
      this.simulateProcess(userId, this.budget);
    } else {
      this.errorMessage = 'Budget data is missing!';
      this.loading = false;
    }
  }


  simulateProcess(userId: string, budget: BudgetDTO|null) {
    setTimeout(() => {
      this.loadingMessage = 'Making recommendations based on your skin type...';
      setTimeout(() => {
        this.loadingMessage = 'Filtering out products that contain your allergens...';
        setTimeout(() => {
          this.loadingMessage = 'Calculating based on your budget...';
          this.recommendationService.getRecommendedProducts(userId, budget!).subscribe(
            products => {
              this.recommendations = products;
              this.loading = false;
            },
            error => {
              this.errorMessage = error;
              this.loading = false;
            }
          );
        }, 2000);
      }, 2000);
    }, 2000);
  }

  // generateRecommendations() {
  //   // Primer proizvoda
  //   return [
  //     {
  //       name: 'Nivea 3',
  //       path: 'Creams/Kids/Day',
  //       price: 1900.99,
  //       instruction: 'Apply a small amount on clean skin twice a day.',
  //       vegan: true,
  //       skinTypes: ['Dry'],
  //       benefits: ['Hydration', 'Soothing'],
  //       ingredientIds: ['Aloe Vera', 'Chamomile']
  //     },
  //     {
  //       name: 'L\'Oreal Youth Code',
  //       path: 'Serums/Youngsters/Night',
  //       price: 2500.50,
  //       instruction: 'Use nightly for best results.',
  //       vegan: false,
  //       skinTypes: ['Combination'],
  //       benefits: ['Anti-aging', 'Brightening'],
  //       ingredientIds: ['Hyaluronic Acid', 'Vitamin C']
  //     },
  //     {
  //       name: 'Moisturizing Lotion',
  //       path: 'Lotions/Elderly/Day&Night',
  //       price: 1500.00,
  //       instruction: 'Apply liberally as needed.',
  //       vegan: true,
  //       skinTypes: ['Sensitive'],
  //       benefits: ['Moisturizing', 'Non-irritating'],
  //       ingredientIds: ['Glycerin', 'Panthenol']
  //     }
  //   ];
  // }
}