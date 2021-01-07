import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  // emailValue = '';
  // nameValue = '';
  // passwordValue = '';
  // confirmPasswordValue = '';
  // phoneValue = '';
  // placeholderEmail = 'Email';
  // placeholderName = 'Name';
  // placeholderPassword = 'Password';
  // placeholderConfirmPassword = 'Confirm Password';
  // placeholderPhone = 'Phone';

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) {
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  get form() {return this.registerForm.contains;}

  register() {
    this.authService.register(
      {
        username: this.form.username.value,
        password: this.form.password.value
      }
    )
      .subscribe(success => {
        if (success) {
          this.router.navigate(['/login']);
        }
      })
  }
}
