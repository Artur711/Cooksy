import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Product} from "../model/product";

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

  // getSumOfProducts(products: Product[]): number {
  //   let sum = 0;
  //   products.forEach(product => sum = sum + product.price)
  //   return sum;
  // }

}
