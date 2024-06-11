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
import { CategorySelectionComponent } from './modules/layout/category-selection/category-selection.component';
import { PurchasesHistoryComponent } from './modules/feedback/purchases-history/purchases-history.component';
import { LeaveFeedbackComponent } from './modules/feedback/leave-feedback/leave-feedback.component';
import { CreateProductComponent } from './modules/layout/create-product/create-product.component';
import { ReportMainPageComponent } from './modules/report/report-main-page/report-main-page.component';
import { ReportFeedbackPageComponent } from './modules/report/report-feedback-page/report-feedback-page.component';
import { FeedbackReportsWmyComponent } from './modules/report/feedback-reports-wmy/feedback-reports-wmy.component';
import { FeedbackReportsContaveProductComponent } from './modules/report/feedback-reports-contave-product/feedback-reports-contave-product.component';
import { FeedbackReportsCountComponent } from './modules/report/feedback-reports-count/feedback-reports-count.component';
import { FeedbackReportsThresholdComponent } from './modules/report/feedback-reports-threshold/feedback-reports-threshold.component';
import { PurchaseReportsComponent } from './modules/report/purchase-reports/purchase-reports.component';
import { ReportUserShoppingComponent } from './modules/report/report-user-shopping/report-user-shopping.component';
import { ReportDiscountUsageComponent } from './modules/report/report-discount-usage/report-discount-usage.component';

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
  {path:'forward-chaining-problems-input', component: ProblemsLifestyleInputComponent},
  {path: 'category-selection', component: CategorySelectionComponent},
  {path:'purchases-history', component: PurchasesHistoryComponent},
  {path:'leave-feedback', component: LeaveFeedbackComponent},
  {path: 'create-product', component:CreateProductComponent},
  {path: 'report-management', component: ReportMainPageComponent},
  {path: 'feedback-reports', component: ReportFeedbackPageComponent},
  {path:'feedback-reports-wmy', component:FeedbackReportsWmyComponent},
  {path:'feedback-reports-count', component:FeedbackReportsCountComponent},
  {path:'feedback-reports-countave-product', component: FeedbackReportsContaveProductComponent},
  {path:'feedback-reports-threshold', component: FeedbackReportsThresholdComponent},
  {path: 'purchase-reports', component: PurchaseReportsComponent},
  {path:'user-shopping-report', component: ReportUserShoppingComponent},
  {path:'discount-usage', component: ReportDiscountUsageComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
