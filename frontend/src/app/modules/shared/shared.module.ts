import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LinkComponent } from './link/link.component';
import { MaterialModule } from '../../../infrastructure/material.module';



@NgModule({
  declarations: [LinkComponent],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports:[
    LinkComponent
  ]
})
export class SharedModule { }
