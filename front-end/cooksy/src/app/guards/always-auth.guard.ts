import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlwaysAuthGuard implements CanActivate {
  canActivate(){
    console.log("AlwaysAuthGuard");
    return true;
  }

}
