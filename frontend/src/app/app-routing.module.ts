import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/login/login.component';
import { ForwardChaining1Component } from './modules/layout/forward-chaining1/forward-chaining1.component';
import { BudgetInputComponent } from './modules/layout/budget-input/budget-input.component';
import { HomePageComponent } from './modules/pages/home-page/home-page.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'home-page', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  { path: 'budget-input', component: BudgetInputComponent },
  { path: 'recommendation-process', component: ForwardChaining1Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }