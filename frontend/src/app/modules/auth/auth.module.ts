import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../infrastructure/material.module';
import { SharedModule} from '../shared/shared.module';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MaterialModule,
    SharedModule,
    HttpClientModule
  ]
})
export class AuthModule { }
