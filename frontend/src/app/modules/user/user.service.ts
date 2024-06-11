import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { DiscountDTO } from '../../dto/Discount';
import { UserReportDTO } from '../../dto/User';
import { GiftDTO } from '../../dto/Gift';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private value$ = new BehaviorSubject<any>({});
  selectedValue$ = this.value$.asObservable();


  constructor(private http: HttpClient) { }



  getUserAllergens() : Observable<string[]> {
    const token = localStorage.getItem('user');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<string[]>(environment.apiHost + 'api/user/allergens',{ headers });
  }

  getUserSkinType(): Observable<string> {
    const token = localStorage.getItem('user');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    const url = `${environment.apiHost}api/user/skinType`;
    return this.http.get(url, { headers, responseType: 'text' }).pipe(
      catchError(this.handleError)
    );
  }


  getUserDiscounts(): Observable<DiscountDTO[]> {
    return this.http.get<DiscountDTO[]>(environment.apiHost + 'api/user/discounts');
  }


  getUserGifts(): Observable<GiftDTO[]> {
    return this.http.get<GiftDTO[]>(environment.apiHost + 'api/user/gifts');
  }


  getUsers(): Observable<UserReportDTO[]> {
    return this.http.get<UserReportDTO[]>(`${environment.apiHost}api/users`);
  }
  
  getUnusedUserDiscounts(): Observable<DiscountDTO[]> {
    return this.http.get<DiscountDTO[]>(environment.apiHost + 'api/user/discounts/unused');
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
