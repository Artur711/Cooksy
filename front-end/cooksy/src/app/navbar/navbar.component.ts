import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isCollapsed = true;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  home(): void {
    this.router.navigate(['/products']);
  }

  recipes(): void {
    this.router.navigate(['/recipes']);
  }

  shopping(): void {
    this.router.navigate(['/shopping-lists']);
  }

  favorite(): void {
    this.router.navigate(['/recipes']);
  }

  setting(): void {
    this.router.navigate(['/products']);
  }
}
