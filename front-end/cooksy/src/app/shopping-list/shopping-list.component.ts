import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {ShoppingListService} from "../service/shopping-list.service";
import {of} from "rxjs";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
  products: Product[] = [];
  sumOfProductsPrice: number = 0;
  ultimateProducts: Product[] = [];


  constructor(private productService: ProductService, private shpListService: ShoppingListService) {
  }


  ngOnInit(): void {
    this.shpListService.getUserShoppingList().subscribe(product => product.forEach(element => this.products.push(element)));
    // this.sumOfProductsPrice = this.productService.getSumOfProducts(this.products);
  }
}

