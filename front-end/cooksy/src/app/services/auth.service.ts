import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, mapTo, tap} from "rxjs/operators";
import {LoginData} from "../models/loginData";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private loggedUser?: string;


  constructor(private http: HttpClient) {}
  // firstName: string, lastName: string,
  register(user: {username: string, password: string, email: string}): Observable<boolean> {
    return this.http.post<any>(`${environment.apiUrlHost}/register`, user)
      .pipe(
      mapTo(true),
          catchError(error => {
            alert(error.error + 'register error');
            return of(false);
          }));
  }

  login(user: {username: string, password: string}): Observable<boolean> {
    return  this.http.post<any>(`${environment.apiUrlHost}/login`, user)
      .pipe(
        tap((data: LoginData) => this.doLoginUser(data.username, data.token)),
        mapTo(true),
        catchError(error => {
          alert(error.error + 'login error');
          return of(false);
        }));
  }

  logout() {
    return this.http.post<any>(`${environment.apiUrlHost}/logout`, {})
      .pipe(
      tap(() => this.doLogoutUser()),
      mapTo(true),
      catchError(error => {
      alert(error.error + 'logout error');
      return of(false);
    }));
  }

  isLoggedIn() {
    return !!this.getJwtToken();
  }

  getJwtToken() {
    return localStorage.getItem(this.JWT_TOKEN);
  }

  private doLoginUser(username: string, token: string) {
    console.log(username + " username")
    localStorage.setItem("isLogged", "true");
    this.loggedUser = username;
    this.storeToken(token);
  }

  public doLogoutUser() {
    this.loggedUser = "";
    this.removeToken();
    localStorage.setItem("isLogged", "false");
  }

  private storeToken(token: string) {
    localStorage.setItem(this.JWT_TOKEN, token);
  }

  private removeToken() {
    localStorage.removeItem(this.JWT_TOKEN);
  }

  public getLoggedUser() {
    return this.loggedUser;
  }
}
