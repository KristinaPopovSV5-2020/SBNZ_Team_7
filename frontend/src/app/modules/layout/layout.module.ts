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


@NgModule({
  declarations: [ToolbarComponent, UserToolbarComponent, AdminToolbarComponent, BudgetInputComponent, ProductCardComponent, ForwardChaining1Component],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule,
  ],
  exports: [ToolbarComponent, UserToolbarComponent, AdminToolbarComponent, BudgetInputComponent, ForwardChaining1Component]
})
export class LayoutModule { }
