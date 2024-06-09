import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class RecommendationService {
  private apiUrl = `${environment.apiHost}/recommendations/products`;
  constructor(private http: HttpClient) { }
}
