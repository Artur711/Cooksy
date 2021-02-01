import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../models/product";
import {ShoppingListService} from "../services/shopping-list.service";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
  products: Product[] = [];
  ultimateProducts: Product[] = [];


  constructor(private productService: ProductService, private shpListService: ShoppingListService) {
  }


  ngOnInit(): void {
    this.shpListService.getUserShoppingList().subscribe(product => product.forEach(element => this.products.push(element)));
  }

  printPDF() {
    let win = window.open('', 'PRINT', 'height=400,width=600');

    if (win) {
      win.document.write('<html lang=""><head><title>' + document.title  + '</title>');
      win.document.write('</head><body >');
      win.document.write('<h1>' + document.title  + '</h1>');
      win.document.write('<p><strong>Shopping-list - DD/MM/YYYY</strong></p>');
      win.document.write('<table style="border: black double 2px">')
      win.document.write('<tr><th style="border: black solid 1px">Product Description</th>' +
        '<th style="border: black solid 1px">Amount</th>' +
        '<th style="border: black solid 1px">Grammage</th></tr>');
      for (let i = 0; i < this.products.length; i++) {
        let product = this.products[i];
        win.document.write(`<tr><td style="border: black solid 1px">${product.original}</td>
                <td style="border: black solid 1px; text-align: center;">${product.measuresAmount}</td>
                <td style="border: black solid 1px; text-align: center;">${product.measuresUnitShort}</td></tr>`);
      }
      win.document.write('</table>')
      win.document.write('</body></html>');
      win.document.close();
      win.focus();
      win.print();
      win.close();
    }
  }
}
