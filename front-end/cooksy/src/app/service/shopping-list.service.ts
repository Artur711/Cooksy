import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class ShoppingListService {
  private apiRecipeUrl = `${environment.apiRecipeUrl}/shopping-list`

  constructor(private http: HttpClient) { }

  getUserShoppingList(): Observable<Product[]> {
   return this.http.get<Product[]>(`${this.apiRecipeUrl}/1`);
  }
}


