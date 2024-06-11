export interface ProductDTO {
    id: string;
    name: string;
    path: string;
    price: number;
    instruction: string;
    vegan: boolean;
    skinTypes: string[];
    benefits: string[];
    ingredientIds: string[];
    ingredientNames: string[];
    popular: boolean;
  }
  

export interface ProductSearchDTO {
typeCategory: string;
ageCategory?: string;
usageCategory?: string;
}


export interface ProductFeedbackDTO{
  productId: string;
  name: string;
}