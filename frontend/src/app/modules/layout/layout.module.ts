import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule } from '../shared/shared.module';
import { UserToolbarComponent } from './user-toolbar/user-toolbar.component';
import { AdminToolbarComponent } from './admin-toolbar/admin-toolbar.component';
import { BudgetInputComponent } from './budget-input/budget-input.component';
import { ForwardChaining1Component } from './forward-chaining1/forward-chaining1.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { ProblemsLifestyleInputComponent } from './problems-lifestyle-input/problems-lifestyle-input.component';
import { ForwardChaining2Component } from './forward-chaining2/forward-chaining2.component';
import { CategorySelectionComponent } from './category-selection/category-selection.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { PurchaseDialogComponent } from './purchase-dialog/purchase-dialog.component';
import { DiscountsComponent } from './discounts/discounts.component';
@NgModule({
  declarations: [ToolbarComponent,DiscountsComponent,  PurchaseDialogComponent ,CategorySelectionComponent, UserToolbarComponent, AdminToolbarComponent, BudgetInputComponent, ProductCardComponent, ForwardChaining1Component,ProblemsLifestyleInputComponent,ForwardChaining2Component, CreateProductComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule,
  ],
  exports: [ToolbarComponent, PurchaseDialogComponent, CreateProductComponent, CategorySelectionComponent, UserToolbarComponent, AdminToolbarComponent, BudgetInputComponent, ProductCardComponent, ForwardChaining1Component,ProblemsLifestyleInputComponent,ForwardChaining2Component]
})
export class LayoutModule { }
