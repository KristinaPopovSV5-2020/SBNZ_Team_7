import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

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

}
