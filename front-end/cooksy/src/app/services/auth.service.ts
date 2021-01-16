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

  constructor() {}

  logout(): void {
    localStorage.setItem('isLoggedIn', "false");
    localStorage.removeItem('token');
  }

  // register(user: {id: number, type: number, username: string, password: string, firstName: string, lastName: string, email: string, photoUrl: string}): Observable<boolean> {
  //   return this.http.post<any>(`${environment.apiUrl}/register`, user)
  //     .pipe(
  //     mapTo(true),
  //         catchError(error => {
  //           alert(error.error + 'register error');
  //           return of(false);
  //         }));
  // }

}

  // private readonly JWT_TOKEN = 'JWT_TOKEN';
  // private loggedUser?: string;
  //
  // constructor(private http: HttpClient) {}
  //
  // register(user: {id: number, type: number, username: string, password: string, firstName: string, lastName: string, email: string, photoUrl: string}): Observable<boolean> {
  //   return this.http.post<any>(`${environment.apiUrl}/register`, user)
  //     .pipe(
  //     mapTo(true),
  //         catchError(error => {
  //           alert(error.error + 'register error');
  //           return of(false);
  //         }));
  // }
  //
  // login(user: {username: string, password: string}): Observable<boolean> {
  //
  //   console.log(user.username);
  //   console.log(user.password);
  //
  //   return this.http.post<any>(`${environment.apiUrl}/login`, user, {withCredentials: true})
  //     .pipe(
  //       tap( (data: LoginData) => this.doLoginUser(data.username, data.jwtToken)),
  //       mapTo(true),
  //       catchError(error => {
  //         alert(error.error + 'login error');
  //         return of(false);
  //       }));
  // }
  //
  // logout() {
  //   return this.http.post<any>(`${environment.apiUrl}/logout`, {}).pipe(
  //     tap(() => this.doLogoutUser()),
  //     mapTo(true),
  //     catchError(error => {
  //     alert(error.error + 'logout error');
  //     return of(false);
  //   }));
  // }
  //
  // isLoggedIn() {
  //   return !!this.getJwtToken();
  // }
  //
  // getJwtToken() {
  //   return localStorage.getItem(this.JWT_TOKEN);
  // }
  //
  // private doLoginUser(username: string, token: string) {
  //   this.loggedUser = username;
  //   this.storeToken(token);
  // }
  //
  // private doLogoutUser(){
  //   this.loggedUser = "";
  //   this.removeToken();
  // }
  //
  // private storeToken(token: string) {
  //   localStorage.setItem(this.JWT_TOKEN, token);
  // }
  //
  // private removeToken() {
  //   localStorage.removeItem(this.JWT_TOKEN);
  // }
