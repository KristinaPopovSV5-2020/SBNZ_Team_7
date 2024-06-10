import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PurchaseHistory } from './purchases-history/purchases-history.component';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private value$ = new BehaviorSubject<any>({});
  selectedValue$ = this.value$.asObservable();


  constructor(private http: HttpClient) { }





  getAllUserPurchases() : Observable<PurchaseHistory[]>{
    const token = localStorage.getItem('user');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<PurchaseHistory[]>(environment.apiHost + 'api/shoppings/user',{ headers })
  }

}
