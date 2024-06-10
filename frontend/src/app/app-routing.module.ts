import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/login/login.component';
import { SignupComponent } from './modules/auth/signup/signup.component';
import { ForwardChaining1Component } from './modules/layout/forward-chaining1/forward-chaining1.component';
import { BudgetInputComponent } from './modules/layout/budget-input/budget-input.component';
import { HomePageComponent } from './modules/pages/home-page/home-page.component';
import {StartpageComponent} from "./modules/pages/startpage/startpage.component";
import { AdminHomeComponent } from './modules/pages/admin-home/admin-home.component';
import { ProblemsLifestyleInputComponent } from './modules/layout/problems-lifestyle-input/problems-lifestyle-input.component';
import { ForwardChaining2Component } from './modules/layout/forward-chaining2/forward-chaining2.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home',component: HomePageComponent },
  {path: 'home-page', component: StartpageComponent},
  {path: 'admin-page', component: AdminHomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  { path: 'budget-input', component: BudgetInputComponent },
  { path: 'recommendation-process', component: ForwardChaining1Component },
  {path:'recommendation-process-second', component: ForwardChaining2Component},
  {path:'forward-chaining-problems-input', component: ProblemsLifestyleInputComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
