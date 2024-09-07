import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerRouting } from "./customer.routing";

@NgModule({
  imports: [
    CommonModule,
    CustomerRouting
  ]
})
export class CustomerModule { }
