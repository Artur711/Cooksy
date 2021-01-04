import { Injectable } from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, observable} from "rxjs";
import {Recipe} from "./recipe";

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private recipeUrl = `${environment.apiUrl}/recipes`

  constructor(private http: HttpClient) { }

  getRecipes(): Observable<Recipe[]> {
    return this.http
      .get<Recipe[]>(this.recipeUrl);
  }
}
