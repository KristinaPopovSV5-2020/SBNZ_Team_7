import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { PurchaseHistory } from './purchases-history/purchases-history.component';
import { FeedbackDTO } from './leave-feedback/leave-feedback.component';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private value$ = new BehaviorSubject<any>({});
  selectedValue$ = this.value$.asObservable();

  private selectedItemSubject = new BehaviorSubject<PurchaseHistory | null>(null);
  selectedItem$ = this.selectedItemSubject.asObservable();


  constructor(private http: HttpClient) { }

  setSelectedItem(item: PurchaseHistory): void {
    this.selectedItemSubject.next(item);
  }

  clearSelectedItem(): void {
    this.selectedItemSubject.next(null);
  }



  getAllUserPurchases() : Observable<PurchaseHistory[]>{
    const token = localStorage.getItem('user');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<PurchaseHistory[]>(environment.apiHost + 'api/shoppings/user',{ headers })
  }

  addFeedback(feedbackDTO: FeedbackDTO) : Observable<any>{
    const token = localStorage.getItem('user');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.post<any>(environment.apiHost + 'api/feedback',feedbackDTO,{ headers })
  }

}
