import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";
import {MenuComponent} from "../menu/menu.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Output()
  onNavigationChange = new EventEmitter<string>();

  public isCollapsed = true;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  onNavItemClick(title: string) {
    this.onNavigationChange.emit(title);
  }
}
