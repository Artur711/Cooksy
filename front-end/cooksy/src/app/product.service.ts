import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, observable} from "rxjs";
import {Product} from "./product";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = `${environment.apiUrl}/api/v2/products`

  constructor(private http: HttpClient) {
  }


  getProducts(): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.productUrl);
  }


}
