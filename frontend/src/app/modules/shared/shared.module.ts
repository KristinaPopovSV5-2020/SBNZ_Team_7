import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LinkComponent } from './link/link.component';
import { MaterialModule } from '../../../infrastructure/material.module';
import { OkDialogComponent } from './ok-dialog/ok-dialog.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';



@NgModule({
  declarations: [LinkComponent, OkDialogComponent, ErrorDialogComponent],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports:[
    LinkComponent,
    OkDialogComponent,
    ErrorDialogComponent
  ]
})
export class SharedModule { }
