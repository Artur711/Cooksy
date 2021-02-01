import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{

  registerForm = this.formBuilder.group({
    username: [''],
    password: [''],
    // firstName: [''],
    // lastName: [''],
    email: ['']
  });


  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) {
  }


  get form() {
    return this.registerForm.controls;
  }

  register() {
    this.authService.register(
      {
        username: this.form.username.value,
        password: this.form.password.value,
        // firstName: this.form.firstName.value,
        // lastName: this.form.lastName.value,
        email: this.form.email.value
      }
    )
      .subscribe(success => {
        if (success) {
          this.router.navigate(['/start/login']);
        }
      })
  }
}
