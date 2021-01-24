import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, mapTo} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Recipes} from "../models/recipes";
import {RecipeDetails} from "../models/recipeDetails";
import {RecipeProduct} from "../models/recipe-product";
import {TypeDish} from "../models/type";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipesUrl = `${environment.apiUrl}/recipes`
  private apiRecipeUrl = `${environment.apiRecipeUrl}/shopping-list`


  constructor(private http: HttpClient) {
  }

  getRecipes(): Observable<Recipes> {
    return this.http
      .get<Recipes>(this.recipesUrl).pipe(
        catchError(this.handleError<Recipes>('getRecipes'))
      );
  }

  getRecipesPage$(page: number, ingredients: string[], equipments: string[], types: TypeDish[]): Observable<Recipes> {
    const ingredient = ingredients.toString().replace(',', '-');
    const equipment = equipments.toString().replace(',', '-');
    const type = types.filter(type => type.isChecked)
      .map(type => type.name.replace(' ', '%20'))
      .toString()
      .replace(',', '-')

    let url = `${this.recipesUrl}?start=${page}`;

    if (ingredient != '') {
      url = url + `&ingredients=${ingredient}`;
    }

    if (equipment != '') {
      url = url + `&equipments=${equipment}`;
    }

    if (type != '') {
      url = url + `&types=${type}`;
    }

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

  addRecipe(recipeID: string | null, userID: string, products: RecipeProduct[]) {
    console.log(recipeID);
    const url = `${this.apiRecipeUrl}/${recipeID}/${userID}`;
    console.log(url);
    return this.http.post<RecipeProduct[]>(url, products).pipe(mapTo(true),
      catchError(error => {
        console.log(error.error);
        return of(false)
      }));
  }
}
