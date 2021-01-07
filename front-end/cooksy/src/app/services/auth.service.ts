import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, map, mapTo, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private loggedUser: string;

  constructor(private http: HttpClient) {}

  register(user: {username: string, password: string}): Observable<boolean> {
    return this.http.post<any>('${environment.apiUrl}/register', user)
      .pipe()
        mapTo(true),
          catchError(error => {
            alert(error.error);
            return of(false);
          });
  }

  login(user: {username: string, password: string}): Observable<boolean> {
    return this.http.post<any>('${environment.apiUrl}/register', user)
      .pipe(
        tap( (data: LoginData) => this.doLoginUser(data.username, data.jwtToken)),
        mapTo(true),
        catchError(error => {
          alert(error.error);
          return of(false);
        }));
  }

  logout() {
    return this.http.post<any>('${environment.apiUrl}/logout', {}).pipe(
      tap(() => this.doLogoutUser()),
      mapTo(true)
    )
  }
}
