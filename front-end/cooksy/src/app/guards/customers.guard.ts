import { Injectable } from '@angular/core';
import {CanActivate, CanLoad, Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class CustomersGuard implements CanActivate, CanLoad {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate() {
    return this.canLoad();
  }

  canLoad() {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
    }
    return this.authService.isLoggedIn();
  }

}
