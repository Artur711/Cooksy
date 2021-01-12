import { Injectable } from '@angular/core';
import {RecipeDetails} from "../model/recipeDetails";
import {environment} from "../../environments/environment";
import {catchError, mapTo} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {FavoriteDto} from "../model/dto";

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  private favoriteUrl = `${environment.apiUrl}/favorites`

  constructor(private http: HttpClient) { }

  getFavorites$(): Observable<FavoriteDto[]> {
    return this.http.get<FavoriteDto[]>(this.favoriteUrl);
    // return this.http.get<any>(this.favoriteUrl).pipe(
    //   map( (data: FavoriteDto[]) => ({
    //     favoriteId: data.favoriteId,
    //     userId: data.user.userId,
    //     recipe: data.recipe
    //   }))
    // );
  }

  addRecipeToFavorite(recipe: RecipeDetails) {
    return this.http.post<RecipeDetails>(this.favoriteUrl, recipe).pipe(
      mapTo(true),
      catchError(error => {
        console.log(error.error);
        return of(false);
      }));
  }

  removeRecipeFromFavorite() {
    return this.http.delete(`${this.favoriteUrl}`).pipe(
      mapTo(true),
      catchError(error => {
        console.log(error.error);
        return of(false);
      }));
  }
}

