import { Component, OnInit } from '@angular/core';
import { ProductDTO } from '../../../dto/Product';

@Component({
  selector: 'app-forward-chaining1',
  templateUrl: './forward-chaining1.component.html',
  styleUrl: './forward-chaining1.component.css'
})
export class ForwardChaining1Component implements OnInit {
  loading = true;
  loadingMessage = 'Getting ready to make most suitable recommendations...';
  recommendations: ProductDTO[] = [];

  ngOnInit() {
    const userId = '663c014482e7b676607dafb2';
    // const budgetData = JSON.parse(localStorage.getItem('budgetData'));
    this.simulateProcess();
  }

  simulateProcess() {
    setTimeout(() => {
      this.loadingMessage = 'Making recommendations based on your skin type...';
      setTimeout(() => {
        this.loadingMessage = 'Filtering out products that contain your allergens...';
        setTimeout(() => {
          this.loadingMessage = 'Calculating based on your budget...';
          setTimeout(() => {
            this.loading = false;
            this.recommendations = this.generateRecommendations();
          }, 1000);
        }, 1000);
      }, 1000);
    }, 1000);
  }

  generateRecommendations() {
    // Primer proizvoda
    return [
      {
        name: 'Nivea 3',
        path: 'Creams/Kids/Day',
        price: 1900.99,
        instruction: 'Apply a small amount on clean skin twice a day.',
        vegan: true,
        skinTypes: ['Dry'],
        benefits: ['Hydration', 'Soothing'],
        ingredientIds: ['Aloe Vera', 'Chamomile']
      },
      {
        name: 'L\'Oreal Youth Code',
        path: 'Serums/Youngsters/Night',
        price: 2500.50,
        instruction: 'Use nightly for best results.',
        vegan: false,
        skinTypes: ['Combination'],
        benefits: ['Anti-aging', 'Brightening'],
        ingredientIds: ['Hyaluronic Acid', 'Vitamin C']
      },
      {
        name: 'Moisturizing Lotion',
        path: 'Lotions/Elderly/Day&Night',
        price: 1500.00,
        instruction: 'Apply liberally as needed.',
        vegan: true,
        skinTypes: ['Sensitive'],
        benefits: ['Moisturizing', 'Non-irritating'],
        ingredientIds: ['Glycerin', 'Panthenol']
      }
    ];
  }
}