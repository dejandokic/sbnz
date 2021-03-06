import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainPageComponent } from './main-page/main-page.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { NewRulesComponent } from './new-rules/new-rules.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/strategy' },
  { path: 'strategy', component: MainPageComponent },
  { path: 'new-rules', component: NewRulesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'not-found', component: ErrorPageComponent },
  { path: '**', redirectTo: '/not-found'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
