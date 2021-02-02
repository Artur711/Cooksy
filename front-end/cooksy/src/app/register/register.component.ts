import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{

  registerForm = this.formBuilder.group({
    username: ['', [Validators.required, this.forbiddenNameValidator(/bob/i)]],
    password: ['', [Validators.required]],
    // firstName: [''],
    // lastName: [''],
    email: ['', [Validators.required, Validators.email]]
  }, {validators: sameNamesValidator});


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
      })
      .subscribe(success => {
        if (success) {
          this.router.navigate(['/start/login']);
        }
      })
  }

  private forbiddenNameValidator(regExp: RegExp): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const forbidden = regExp.test(control.value);
      return forbidden ? {forbiddenName: {value: "You can't be Bob"}} : null;
    }
  }
}

const sameNamesValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const username = control.get('username');
  const password = control.get('password');

  return username?.value && password?.value && username.value === password.value ? { sameNames: true } : null;
}
