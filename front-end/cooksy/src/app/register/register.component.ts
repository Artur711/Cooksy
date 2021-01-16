// import { Component, OnInit } from '@angular/core';
// import {FormBuilder, FormGroup} from "@angular/forms";
// import {Router} from "@angular/router";
// import {AuthService} from "../services/auth.service";
//
// @Component({
//   selector: 'app-register',
//   templateUrl: './register.component.html',
//   styleUrls: ['./register.component.css']
// })
// export class RegisterComponent implements OnInit {
//
//   registerForm = this.formBuilder.group({
//      id: [''],
//      type: [''],
//      username: [''],
//      password: [''],
//      firstName: [''],
//      lastName: [''],
//      email: [''],
//      photoUrl: ['']
//   });
//
//   loginForm = this.formBuilder.group({
//     username: [''],
//     password: ['']
//   });
//
//   constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) {
//   }
//
//   ngOnInit() {
//     this.registerForm = this.formBuilder.group({
//       id: [''],
//       type: [''],
//       username: [''],
//       password: [''],
//       firstName: [''],
//       lastName: [''],
//       email: [''],
//       photoUrl: ['']
//     });
//   }
//
//   get form() {return this.registerForm.controls;}
//
//   register() {
//     this.authService.register(
//       {
//         id: this.form.id.value,
//         type: this.form.type.value,
//         username: this.form.username.value,
//         password: this.form.password.value,
//         firstName: this.form.firstName.value,
//         lastName: this.form.lastName.value,
//         email: this.form.email.value,
//         photoUrl: this.form.photoUrl.value
//       }
//     )
//       .subscribe(success => {
//         if (success) {
//           this.router.navigate(['/register']);
//         }
//       })
//   }
// }
