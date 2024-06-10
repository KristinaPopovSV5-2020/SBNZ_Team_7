import { Injectable } from '@angular/core';
import { ProductDTO } from '../dto/Product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ProblemsLifestyleDTO } from '../modules/layout/problems-lifestyle-input/problems-lifestyle-input.component';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

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
}
