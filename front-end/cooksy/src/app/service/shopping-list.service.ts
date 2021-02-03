import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable, of} from "rxjs";
import {Product} from "../model/product";
import {RecipeProduct} from "../model/recipe-product";
import {catchError, mapTo} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ShoppingListService {
  private apiRecipeUrl = `${environment.apiRecipeUrl}/shopping-list`
  products = new BehaviorSubject<RecipeProduct[]>([]);
  currentProducts = this.products.asObservable();

  constructor(private http: HttpClient) {
  }

  getUserShoppingList(): Observable<[Product[]]> {
    return this.http.get<[Product[]]>(`${this.apiRecipeUrl}`);
  }

  changeList(newProducts: RecipeProduct[]) {
    let currentData = this.products.value;
    let updatedData = currentData.concat(newProducts);
    this.products.next(updatedData.filter(product => product.isChecked));
  }

  addRecipe(products: RecipeProduct[], date: string) {
    const url = `${this.apiRecipeUrl}/add-to-list`;
    const myPostBody = {productDtos: products, date: date}
    return this.http.post<RecipeProduct[]>(url, myPostBody).pipe(mapTo(true),
      catchError(error => {
        console.log(error.error);
        return of(false)
      }));
  }
}


