import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {environment} from "../../environments/environment";
import {Product} from "../model/product";
import {ProductsDto} from "../model/dto";
import {catchError} from "rxjs/operators";

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


  getKrogerProducts$(product: string, page: number): Observable<ProductsDto> {
    return this.http
      .get<ProductsDto>(`${this.productUrl}/${product}?page=${page}`).pipe(
      // .get<ProductsDto>(`${this.productUrl}/milk`).pipe(
        catchError(this.handleError<ProductsDto>(`getKrogerProducts product=`))
      );
  }

  // getSumOfProducts(products: Product[]): number {
  //   let sum = 0;
  //   products.forEach(product => sum = sum + product.price)
  //   return sum;
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
