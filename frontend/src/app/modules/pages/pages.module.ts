import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StartpageComponent } from './startpage/startpage.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';

@NgModule({
  declarations: [StartpageComponent, HomePageComponent, AdminHomeComponent],
  imports: [
    CommonModule
  ],
  exports: [StartpageComponent, HomePageComponent, AdminHomeComponent]
})
export class PagesModule { }
