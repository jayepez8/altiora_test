import { Component, OnDestroy, OnInit } from '@angular/core';
import { CardModule } from "primeng/card";
import { TableModule } from "primeng/table";
import { Customer } from "../../../shared/models/customer";
import { CustomerService } from "../../../core/http/customer.service";
import { CommonModule } from "@angular/common";
import { Button } from "primeng/button";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { CustomerAddEditComponent } from "../customer-add-edit/customer-add-edit.component";
import { MessageService } from "primeng/api";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    TableModule,
    Button
  ],
  providers: [DialogService, MessageService],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.scss'
})
export class CustomerListComponent implements OnInit, OnDestroy{

  customers: Customer[] | any = [];

  ref: DynamicDialogRef | undefined;

  constructor(
    public dialogService: DialogService,
    private customerService:CustomerService
  ) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  ngOnDestroy() {
    if (this.ref) {
      this.ref.close();
    }
  }

  getAll(){
    this.customerService.getAll().subscribe({
      next:(result=>this.customers = result)
    })
  }

  openModalCreate() {
    this.ref = this.dialogService.open(CustomerAddEditComponent, {header: 'Create new customer'});
    this.ref.onClose.subscribe((customer:Customer)=>{
      if(customer){
        this.getAll();
      }
    })
  }
}
