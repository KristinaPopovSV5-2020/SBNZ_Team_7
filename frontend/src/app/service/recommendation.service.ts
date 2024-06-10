import { Injectable } from '@angular/core';
import { ProductDTO } from '../dto/Product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ProblemsLifestyleDTO } from '../modules/layout/problems-lifestyle-input/problems-lifestyle-input.component';
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
  private value$ = new BehaviorSubject<any>({});
  selectedValue$ = this.value$.asObservable();


  constructor(private http: HttpClient) { }



  recommendProductsBasedOnSkinProblemsAndHabits(problemsLifestyleDTO: ProblemsLifestyleDTO): Observable<ProductDTO[]> {
    const token = localStorage.getItem('user');
    const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    });
    return this.http.post<ProductDTO[]>(environment.apiHost + 'api/recommendations/products/problems-habits', problemsLifestyleDTO, { headers });
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
