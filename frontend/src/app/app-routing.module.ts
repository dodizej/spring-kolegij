import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HardwaresComponent} from "./hardwares/hardwares.component";
import {HardwareDetailComponent} from "./hardware-detail/hardware-detail.component";
import {LoginComponent} from "./login/login.component";
import {LoggedInGuard} from "./security/logged-in.guard";
import {HomeComponent} from "./home/home.component";
import {ForbiddenPageComponent} from "./forbidden-page/forbidden-page.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {AdminAuthorityGuard} from "./security/admin-authority.guard";
import {HardwareNewComponent} from "./hardware-new/hardware-new.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'hardwares',
    component: HardwaresComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'detail/:code',
    component: HardwareDetailComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'new-hardware',
    component: HardwareNewComponent,
    canActivate: [AdminAuthorityGuard]
  },
  {
    path: 'forbidden',
    component: ForbiddenPageComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
