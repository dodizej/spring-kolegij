import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import jwt_decode from "jwt-decode";
import {Login} from "./login.model";
import {Jwt} from "./jwt.model";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  rootUrl = 'http://localhost:8080/authentication';

  constructor(private http: HttpClient) {
  }

  login(login: Login) {
    return this.http.post<Jwt>(`${this.rootUrl}/login`, login);
  }

  saveJwtToLocalStorage(jwt: string) {
    localStorage.setItem('accessToken', jwt);
  }

  isUserAuthenticated() {
    const decodedToken = this.decodeJwt();
    let usernameKey = null;
    if (decodedToken != null) {
      usernameKey = (Object.keys(decodedToken) as (keyof typeof decodedToken)[]).find((key) => {
        return key === 'sub' && decodedToken[key] != null;
      });
    }
    return usernameKey != null;
  }

  getAuthenticatedUserUsername() {
    const decodedToken = this.decodeJwt();
    let username = null;
    if (decodedToken != null) {
      const usernameKey = (Object.keys(decodedToken) as (keyof typeof decodedToken)[]).find((key) => {
        return key === 'sub' && decodedToken[key] != null;
      });
      if (usernameKey != null) {
        username = decodedToken[usernameKey]
      }
    }
    return username;
  }

  isUserAdmin(): boolean {
    const adminRoleName = 'ROLE_ADMIN';
    const decodedToken = this.decodeJwt();
    let authorities: any = null;
    if (decodedToken != null) {
      const usernameKey = (Object.keys(decodedToken) as (keyof typeof decodedToken)[]).find((key) => {
        // @ts-ignore
        return key === 'authorities' && decodedToken[key] != null;
      });
      if (usernameKey != null) {
        authorities = decodedToken[usernameKey]
      }
    }
    return authorities != null && authorities.includes(adminRoleName);
  }

  logout() {
    localStorage.removeItem('accessToken');
  }

  decodeJwt(): string | null {
    let token = localStorage.getItem('accessToken');
    if (token != null) {
      return jwt_decode(token);
    } else {
      return null;
    }
  }

}
