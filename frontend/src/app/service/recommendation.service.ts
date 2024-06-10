import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { BudgetDTO } from '../dto/Budget';
import { Observable, catchError, throwError } from 'rxjs';
import { ProductDTO, ProductSearchDTO } from '../dto/Product';
@Injectable({
  providedIn: 'root'
})
export class RecommendationService {
  private apiUrl = `${environment.apiHost}api/`;
  constructor(private http: HttpClient) { }


  getRecommendedProducts(userId: string, budget: BudgetDTO): Observable<ProductDTO[]> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});

    const url = `${this.apiUrl}recommendations/products?userId=${userId}`;
    return this.http.post<ProductDTO[]>(url, budget, { headers }).pipe(
      catchError(this.handleError)
    );
  }


  searchProducts(searchDTO: ProductSearchDTO): Observable<ProductDTO[]> {
    console.log(searchDTO);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    const url = `${this.apiUrl}products/backward`;
    return this.http.post<ProductDTO[]>(url, searchDTO, { headers }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Backend error
      errorMessage = `Server returned code: ${error.status}, error message is: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
