import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {MenuComponent} from "../menu/menu.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isCollapsed = true;

  constructor(private router: Router,
              private menu: MenuComponent) {
  }

  ngOnInit(): void {
  }

  home(): void {
    this.menu.title = 'Home';
    this.router.navigate(['/home']);
  }

  recipes(): void {
    this.menu.title = 'All Recipes';
    this.router.navigate(['/recipes']);
  }

  shopping(): void {
    this.menu.title = 'Shopping lists';
    this.router.navigate(['/shopping-lists']);
  }

  favorite(): void {
    this.menu.title = 'Favorite recipes';
    this.router.navigate(['/recipes']);
  }

  setting(): void {
    this.menu.title = 'Account Setting';
    this.router.navigate(['/products']);
  }
}
