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
    ingredientNames: string[]
  }
  

export interface ProductSearchDTO {
typeCategory: string;
ageCategory?: string;
usageCategory?: string;
}