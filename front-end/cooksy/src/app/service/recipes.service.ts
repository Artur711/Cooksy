import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Recipes} from "../model/recipes";
import {Details} from "../model/details";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipesUrl = `${environment.apiUrl}/recipes`
  private apiRecipeUrl = `${environment.apiRecipeUrl}/recipes`


  constructor(private http: HttpClient) {
  }

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

  getRecipeDetail(id: string | null): Observable<Details> {
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

  addRecipe(recipeID: string | null, userID: string) {
    console.log(recipeID);
    const url = `${this.apiRecipeUrl}/${recipeID}/${userID}`;
    console.log(url);
    this.http.get(url);
  }
}
