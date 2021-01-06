import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, observable} from "rxjs";
import {Product} from "./product";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = `${environment.apiUrl}/products`

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.productUrl);
  }

  getSumOfProducts(products: Product[]): number {
    let sum = 0;
    products.forEach(product => sum = sum + product.price)
    return sum;
  }

}
