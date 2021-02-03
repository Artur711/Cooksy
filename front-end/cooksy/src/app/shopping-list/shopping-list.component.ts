import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {ShoppingListService} from "../service/shopping-list.service";
import {formatDate} from '@angular/common';
import {RecipeProduct} from "../model/recipe-product";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
  allProducts: Product[] = [];
  sumOfProductsPrice: number = 0;
  chosenListProducts: Product[] = []
  toBeConfirmedProducts: RecipeProduct[] = [];
  date: string;
  userLists: string[] = [];


  constructor(private productService: ProductService,
              private shpListService: ShoppingListService) {
    this.date = formatDate(new Date(), 'yyyy/MM/dd-h:mm:ss', 'en');
  }


  ngOnInit(): void {
    this.shpListService.currentProducts.subscribe(products => this.toBeConfirmedProducts = products);
    this.shpListService.getUserShoppingList().subscribe(products => products.forEach(products => products.forEach(product => this.allProducts.push(product))));
    this.shpListService.getUserShoppingList().subscribe(products => products.forEach(products => products.forEach(product => {
      if (!this.userLists.includes(product.date)) {
        this.userLists.push(product.date)
      }
    })));

    ///\/\/\/\/\/\/\/\/\ does not work --> dont know why :(
    this.userLists = this.userLists.sort((a, b) => (a > b ? -1 : 1));
    // this.userLists.sort((a, b) => b - a)
  }

  productsApproval(id: number) {
    let index = this.toBeConfirmedProducts.findIndex(product => product.productId == id);
    this.toBeConfirmedProducts[index].isChecked = !this.toBeConfirmedProducts[index].isChecked;
  }

  amountAddition(id: number) {
    let index = this.toBeConfirmedProducts.findIndex(product => product.productId == id);
    this.toBeConfirmedProducts[index].measuresAmount = this.toBeConfirmedProducts[index].measuresAmount + 1;
  }

  amountDeletion(id: number) {
    let index = this.toBeConfirmedProducts.findIndex(product => product.productId == id);
    this.toBeConfirmedProducts[index].measuresAmount = this.toBeConfirmedProducts[index].measuresAmount - 1;
  }

  addToDB(ultimateProducts: RecipeProduct[]) {
    this.shpListService.addRecipe(ultimateProducts.filter(product => product.isChecked), this.date).subscribe();
    this.toBeConfirmedProducts.length = 0;

  }

  uploadContent(userList: any) {
    this.chosenListProducts = [];
    this.allProducts.forEach(product => {
      if (product.date == userList) {
        this.chosenListProducts.push(product)
      }
    })
  }
}

