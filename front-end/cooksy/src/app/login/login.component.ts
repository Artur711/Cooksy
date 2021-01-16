import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {ILogin} from "../models/ILogin";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {tap} from "rxjs/operators";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: ILogin = { username: "admin", password: "admin123"};
  loginForm: FormGroup;
  message: string;
  returnUrl: string;


  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router ) {
    this.message = "";
    this.returnUrl = "";
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.returnUrl = "/home";
    this.authService.logout();
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }else{
      if(this.form.username.value == this.model.username && this.form.password.value == this.model.password){
        console.log("Login successful!");
        localStorage.setItem('isLoggedIn', "true");
        localStorage.setItem('token', this.form.username.value);
        this.router.navigate([this.returnUrl]);
      }else{
        this.message = "Please check your userid and password";
      }
    }
  }

  // login(user: {username: string, password: string}): Observable<boolean> {
  //
  //   console.log(user.username);
  //   console.log(user.password);
  //
  //   return this.http.post<any>(`${environment.apiUrl}/login`, user, {withCredentials: true})
  //     .pipe(
  //       tap( (data: LoginData) => this.doLoginUser(data.username, data.jwtToken)),
  //       mapTo(true),
  //       catchError(error => {
  //         alert(error.error + 'login error');
  //         return of(false);
  //       }));
  // }

  get form() {return this.loginForm.controls;}
}
