import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthService} from "../services/auth.service";


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate() {
    // if (this.authService.isLoggedIn()) {
    //   this.router.navigate(['/menu']).then(r => console.log("OnlyLoggedInUsers"));
    // }
    // console.log("OnlyLoggedInUsers");
    if (this.authService.isLoggedIn()) {
      return true;
    } else {
      window.alert("You don't have permission to view this page");
      return false;
    }
  }
}
