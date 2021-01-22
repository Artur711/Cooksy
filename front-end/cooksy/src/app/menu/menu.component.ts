import {Component} from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  public title = "Home";

  constructor(private authService: AuthService) { }

  onNavigationChange(newTitle: string): void {
    this.title = newTitle;
  }

  logout(){
    this.authService.doLogoutUser();
  }

}
