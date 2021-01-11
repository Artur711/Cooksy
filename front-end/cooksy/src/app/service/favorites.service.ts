import { Injectable } from '@angular/core';
import {Details} from "../model/details";
import {environment} from "../../environments/environment";
import {catchError, mapTo} from "rxjs/operators";
import {of} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  private favoriteUrl = `${environment.apiUrl}/favorites`

  constructor(private http: HttpClient) { }

  addRecipeToFavorite(recipe: Details) {
    return this.http.post<Details>(this.favoriteUrl, recipe).pipe(
      mapTo(true),
      catchError(error => {
        console.log(error.error);
        return of(false);
      }));
  }
}

