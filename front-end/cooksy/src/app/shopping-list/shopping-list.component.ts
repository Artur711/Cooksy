import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {ShoppingListService} from "../service/shopping-list.service";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
  products: Product[] = [];
  sumOfProductsPrice: number = 0;


  constructor(private productService: ProductService, private shpListService: ShoppingListService) {
  }


  ngOnInit(): void {
    // this.products = [
    //   {
    //     productId: 1,
    //     name: 'product1',
    //     price: 12,
    //     productTypeId: 1,
    //     marketId: 1,
    //     grammage: {gmId: 1, grammage: 'L', quantity: 3}
    //   },
    //   {
    //     productId: 2,
    //     name: 'product2',
    //     price: 1,
    //     productTypeId: 1,
    //     marketId: 1,
    //     grammage: {gmId: 1, grammage: 'KG', quantity: 3}
    //   },
    //   {
    //     productId: 3,
    //     name: 'product3',
    //     price: 2,
    //     productTypeId: 1,
    //     marketId: 1,
    //     grammage: {gmId: 1, grammage: 'KG', quantity: 3}
    //   },
    //   {
    //     productId: 4,
    //     name: 'product4',
    //     price: 10,
    //     productTypeId: 1,
    //     marketId: 1,
    //     grammage: {gmId: 1, grammage: 'L', quantity: 3}
    //   },
    // ]
    this.shpListService.getUserShoppingList().subscribe(product => product.forEach(element => this.products.push(element)));
    // this.sumOfProductsPrice = this.productService.getSumOfProducts(this.products);
  }
}
