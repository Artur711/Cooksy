import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Recipes} from "../model/recipes";
import {RecipeDetails} from "../model/recipeDetails";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipesUrl = `${environment.apiUrl}/recipes`
  private apiRecipeUrl = `${environment.apiUrl}/recipes`


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

  addRecipe(recipeID: string, userID: string) {
    const url = `${this.apiRecipeUrl}/${recipeID}/${userID}`;
    this.http.get(url);
  }
}
