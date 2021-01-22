import {Component} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

 loginForm = this.formBuilder.group({
   username: [''],
   password: ['']
 });

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router ) {}

  get form() {return this.loginForm.controls;}

  login() {
    this.authService.login(
      {
        username: this.form.username.value,
        password: this.form.password.value
      })
      .subscribe(success => {
        console.log(success)
        if (success) {
          this.router.navigate(['/home']);
        }
      })
  }
}
