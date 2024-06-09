import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule } from '../shared/shared.module';
import { UserToolbarComponent } from './user-toolbar/user-toolbar.component';
import { AdminToolbarComponent } from './admin-toolbar/admin-toolbar.component';



@NgModule({
  declarations: [ToolbarComponent, UserToolbarComponent, AdminToolbarComponent],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule,
  ],
  exports: [ToolbarComponent, UserToolbarComponent, AdminToolbarComponent]
})
export class LayoutModule { }
