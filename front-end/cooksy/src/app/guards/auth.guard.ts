import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "../services/auth.service";


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    let url: string = state.url;
    return this.verifyLogin(url);
  }

  // @ts-ignore
  verifyLogin(url: string): boolean {
    if(!this.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }else if(this.isLoggedIn()) {
      return true;
    }
  }

  public isLoggedIn(): boolean {
    let status = false;
    if (localStorage.getItem('isLoggedIn') == "true") {
      status = true;
    }else{
      status = false;
    }
    return status
  }
}

  // constructor(private router: Router) { }
  // canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
  //   if (this.isLoggedIn()) {
  //     return true;
  //   }
  //   // navigate to login page as user is not authenticated
  //   this.router.navigate(['/login']);
  //   return false;
  // }
  // public isLoggedIn(): boolean {
  //   let status = false;
  //   if (localStorage.getItem('isLoggedIn') == "true") {
  //     status = true;
  //   }
  //   else {
  //     status = false;
  //   }
  //   return status;
  // }

