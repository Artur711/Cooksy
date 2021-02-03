import { Component} from '@angular/core';
import {AbstractControl, FormBuilder, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent{


  registerForm = this.formBuilder.group({
    username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
    password: ['', [Validators.required, Validators.minLength(7), Validators.maxLength(25)]],
    // firstName: [''],
    // lastName: [''],
    // , [this.validatorAsyncEmail.bind(this)] validatory async idą na trzecie miejsce
    email: ['', [Validators.required, Validators.email]]
  }, {validators: sameNamesValidator});


  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router, private http: HttpClient) {

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
          this.router.navigate(['/login']);
        }
      })
  }
}



const sameNamesValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const username = control.get('username');
  const password = control.get('password');

  return username?.value && password?.value && username.value === password.value ? { sameNames: true } : null;
}
