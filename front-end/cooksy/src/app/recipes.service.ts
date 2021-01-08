import { Injectable } from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Recipes} from "./recipes";
import {Details} from "./details";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipesUrl = `${environment.apiUrl}/recipes`

  constructor(private http: HttpClient) { }

  getRecipes(): Observable<Recipes> {
    return this.http
      .get<Recipes>(this.recipesUrl).pipe(
        catchError(this.handleError<Recipes>('getRecipes'))
      );
  }

  getRecipesPage(page: number): Observable<Recipes> {
    const url = `${this.recipesUrl}?page=${page}`;
    return this.http.get<Recipes>(url).pipe(
      catchError(this.handleError<Recipes>('getRecipesPage'))
    );
}

  getRecipeDetail(id: number): Observable<Details> {
    const url = `${this.recipesUrl}/recipe-detail/${id}`;
    return this.http.get<Details>(url).pipe(
      catchError(this.handleError<Details>(`getRecipeDetail id=${id}`))
    );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
