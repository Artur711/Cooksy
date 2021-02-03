import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, map, mapTo} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Recipes} from "../model/recipes";
import {RecipeDetails} from "../model/recipeDetails";
import {RecipeProduct} from "../model/recipe-product";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipesUrl = `${environment.apiUrl}/recipes`



  constructor(private http: HttpClient) {
  }

  getRecipes(): Observable<Recipes> {
    return this.http
      .get<Recipes>(this.recipesUrl).pipe(
        catchError(this.handleError<Recipes>('getRecipes'))
      );
  }

  getRecipesPage$(page: number, ingredient: string): Observable<Recipes> {
    const url = ingredient == '' || ingredient == null ?
      `${this.recipesUrl}?page=${page}` :
      `${this.recipesUrl}/${ingredient}?page=${page}`;

    return this.http.get<Recipes>(url).pipe(
      catchError(this.handleError<Recipes>('getRecipesPage'))
    );
  }

  getRecipeDetail$(id: string | null): Observable<RecipeDetails> {
    const url = `${this.recipesUrl}/recipe-detail/${id}`;
    return this.http.get<RecipeDetails>(url).pipe(
      catchError(this.handleError<RecipeDetails>(`getRecipeDetail id=${id}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }


}
