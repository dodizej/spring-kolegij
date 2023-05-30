import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../security/authentication.service";
import {Login} from "../security/login.model";
import {Jwt} from "../security/jwt.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authenticating = false;
  login = new Login('', '');
  authenticationError = false;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    if(this.authenticationService.isUserAuthenticated()) {
      this.router.navigate(['/home']).then();
    }
  }

  buttonLoginClick() {
    this.authenticationError = false;
    this.authenticating = true;

    this.authenticationService.login(this.login).subscribe(
      {
        next: (loginResponse: Jwt) => {
          this.authenticationService.saveJwtToLocalStorage(loginResponse.jwt);
          this.router.navigate(['/home']).then();
        },
        error: () => {
          this.authenticationError = true;
          this.authenticating = false;
        }
      })
  }

}
