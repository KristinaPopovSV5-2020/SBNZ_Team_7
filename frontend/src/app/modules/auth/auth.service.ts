import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Token } from './model/token';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User } from './model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    skip: 'true',
    });
  private user$ = new BehaviorSubject<string | null>("");
  userState$ = this.user$.asObservable();

  private headersReg = new HttpHeaders({
    'Access-Control-Allow-Methods':'*',
    'Content-type':'application/json'
  });

  
  constructor(private http: HttpClient) {
    this.user$.next(this.getRole());
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem('user') != null) {
      return true;
    }
    return false;
  }

  login(auth: LoginDTO): Observable<Token> {
    return this.http.post<Token>(environment.apiHost + 'api/login', auth, {
      headers: this.headers,
    });
  }

  logout(): Observable<string> {
    return this.http.get(environment.apiHost + 'api/logout', {
      responseType: 'text',
    });
  }


  getUsername(): string | null {
    if (this.isLoggedIn()) {
      const accessToken: string = localStorage.getItem('user') || '';
      const helper = new JwtHelperService();
      const username = helper.decodeToken(accessToken).sub;
      return username;
    }
    return null;
  }


  getId(): number{
    const accessToken: string= localStorage.getItem('user') || '';
    const helper = new JwtHelperService();
    const id = helper.decodeToken(accessToken).id;
    return id;
  }

  getRole(): string | null {
    if (this.isLoggedIn()) {
      const accessToken: string = localStorage.getItem('user') || '';
      const helper = new JwtHelperService();
      const role = helper.decodeToken(accessToken).role[0].name;
      return role;
    }
    return null;
  }


  setUser(): void {
    this.user$.next(this.getRole());
  }

  getUrlPath(): string {
    if (this.getRole() =="User") {
        return "home-page";
    }else if (this.getRole() == "Admin") {
      return "admin-page";
      
    }
    return "";
  }

  register(user: User): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'api/user/register', user,  {headers: this.headersReg});
  }

  getAllergens():Observable<any>{
    return this.http.get<any>(environment.apiHost + 'api/ingredient/allergens');
  }


}


export interface LoginDTO{
  username: string | null |undefined,
  password: string | null | undefined

}

