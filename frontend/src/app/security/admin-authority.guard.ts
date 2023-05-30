import {Injectable} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AdminAuthorityGuard implements CanActivate, CanActivateChild {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean | UrlTree {
    return this.canActivateRootAndChild(next, state);
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.canActivateRootAndChild(childRoute, state);
  }

  canActivateRootAndChild(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    if (this.authenticationService.isUserAuthenticated()) {
      if(this.authenticationService.isUserAdmin()) {
        return true;
      } else {
        return this.router.parseUrl('/forbidden')
      }
    } else {
      return this.router.parseUrl('/login')
    }
  }
}
