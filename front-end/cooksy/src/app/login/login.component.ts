import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {ILogin} from "../models/ILogin";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: ILogin = { userid: "admin", password: "admin123"};
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
      if(this.form.userid.value == this.model.userid && this.form.password.value == this.model.password){
        console.log("Login successful!");
        localStorage.setItem('isLoggedIn', "true");
        localStorage.setItem('token', this.form.userid.value);
        this.router.navigate([this.returnUrl]);
      }else{
        this.message = "Please check your userid and password";
      }
    }
  }

  get form() {return this.loginForm.controls;}
}
