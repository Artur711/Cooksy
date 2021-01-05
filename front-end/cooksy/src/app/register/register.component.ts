import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  emailValue = '';
  nameValue = '';
  passwordValue = '';
  confirmPasswordValue = '';
  phoneValue = '';
  placeholderEmail = 'Email';
  placeholderName = 'Name';
  placeholderPassword = 'Password';
  placeholderConfirmPassword = 'Confirm Password';
  placeholderPhone = 'Phone';

  constructor() { }

  ngOnInit(): void {
  }

}
