import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StartpageComponent } from './startpage/startpage.component';
import { HomePageComponent } from './home-page/home-page.component';

@NgModule({
  declarations: [StartpageComponent, HomePageComponent],
  imports: [
    CommonModule
  ],
  exports: [StartpageComponent, HomePageComponent]
})
export class PagesModule { }
