import { Component } from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  constructor() {
  }

  isLogged = localStorage.getItem('isLoggedIn');

  // public isLogged() {
  //   localStorage.setItem("isLoggedIn", "true");
  //   console.log(localStorage.getItem("isLoggedIn") + " odp from main component")
  //   return localStorage.getItem("isLoggedIn");
  // }
}
