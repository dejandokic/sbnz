import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { IUserRegister, AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signupForm: FormGroup;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      return;
    }

    const { email, password, name, username } = this.signupForm.value;
    const newUser: IUserRegister = {
      email,
      password,
      username,
      name
    };

    this.errorMessage = '';

    this.authService.signup(newUser).subscribe(
      resData => {
        console.log(resData);
        this.signupForm.reset();
        this.router.navigate(['/login']);
      },
      error => {
        if (error.status === 400) {
          this.errorMessage = error.error.message;
        } else {
          console.log(error);
          this.errorMessage = 'Error';
        }
      }
    );
  }

  get username() {
    return this.signupForm.get('username');
  }

  get name() {
    return this.signupForm.get('name');
  }

  get email() {
    return this.signupForm.get('email');
  }

  get password() {
    return this.signupForm.get('password');
  }

}
