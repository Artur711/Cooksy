import { Component } from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLogged = true;

  constructor(private authService: AuthService) {
  }


  public checkIsLogged() {
    console.log(this.authService.isLoggedIn() + "app is Logged IN")
    console.log(this.authService.getLoggedUser())
    return this.authService.isLoggedIn();
  }
}
