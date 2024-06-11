import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { ProductDTO } from '../dto/Product';
import { IngredientDTO } from '../dto/Ingredient';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = `${environment.apiHost}api/`;
  constructor(private http: HttpClient) { }

  private value$ = new BehaviorSubject<any>({});
  selectedValue$ = this.value$.asObservable();


  addProduct(product: ProductDTO): Observable<boolean> {
    console.log(product);
    console.log(this.apiUrl + "products" )
    return this.http.post<boolean>(this.apiUrl + "products", product);
  }

  buyProduct(productId: string, discountId: string | null): Observable<any> {
    console.log('buyProduct service called with productId:', productId, 'and discountId:', discountId);
    let params = new HttpParams().set('productId', productId);
    if (discountId) {
      params = params.set('discountId', discountId);
    }
    return this.http.post<any>(`${this.apiUrl}shoppings`, null, { params });
  }
  

  getIngredients(): Observable<IngredientDTO[]> {
    
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get<IngredientDTO[]>(this.apiUrl + 'ingredient/all', { headers: headers });
  }
}
