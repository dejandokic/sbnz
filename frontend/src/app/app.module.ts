import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CustomMaterialModule } from './shared/material.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptorService } from './services/auth-interceptor.service';
import { DatePipe } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NewRulesComponent } from './new-rules/new-rules.component';
import { NewRulesEarlyComponent } from './new-rules/new-rules-early/new-rules-early.component';
import { NewRulesMidLateComponent } from './new-rules/new-rules-mid-late/new-rules-mid-late.component';
import { NewRulesEarlyJungleComponent } from './new-rules/new-rules-early-jungle/new-rules-early-jungle.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    SignUpComponent,
    ErrorPageComponent,
    MainPageComponent,
    NewRulesComponent,
    NewRulesEarlyComponent,
    NewRulesMidLateComponent,
    NewRulesEarlyJungleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    CustomMaterialModule,
    HttpClientModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }, DatePipe ],
  bootstrap: [AppComponent]
})
export class AppModule { }
