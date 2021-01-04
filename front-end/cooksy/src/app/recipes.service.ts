import { Injectable } from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, observable} from "rxjs";
import {Recipes} from "./recipes";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipeUrl = `${environment.apiUrl}/recipes`

  constructor(private http: HttpClient) { }

  getRecipes(): Observable<Recipes> {
    return this.http
      .get<Recipes>(this.recipeUrl);
  }
}
