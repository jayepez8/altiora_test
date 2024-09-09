import { Component, OnDestroy, OnInit } from '@angular/core';
import { CardModule } from "primeng/card";
import { TableModule } from "primeng/table";
import { Customer } from "../../../shared/models/customer";
import { CustomerService } from "../../../core/http/customer.service";
import { CommonModule } from "@angular/common";
import { Button } from "primeng/button";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { CustomerAddEditComponent } from "../customer-add-edit/customer-add-edit.component";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ConfirmationService, MessageService } from "primeng/api";
import { ToastModule } from "primeng/toast";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    TableModule,
    Button,
    ConfirmDialogModule,
    ToastModule
  ],
  providers: [DialogService,ConfirmationService,MessageService],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.scss'
})
export class CustomerListComponent implements OnInit, OnDestroy{

  customers: Customer[] | any = [];
  ref: DynamicDialogRef | undefined;

  constructor(
    public dialogService: DialogService,
    private messageService: MessageService,
    private customerService:CustomerService,
    private confirmationService: ConfirmationService
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
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Customer created successfully' });
        this.getAll();
      }
    });
  }

  openModalEdit(customer: Customer) {
    this.ref = this.dialogService.open(CustomerAddEditComponent, {header: 'Edit customer',data:customer});
    this.ref.onClose.subscribe((customer:Customer)=>{
      if(customer){
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Customer updated successfully' });
        this.getAll();
      }
    });
  }

  deleteCustomer(customer: Customer) {
    this.confirmationService.confirm({
      message: `Do you want to delete client ${customer.identification}?`,
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass:"p-button-danger p-button-text",
      rejectButtonStyleClass:"p-button-text p-button-text",
      acceptIcon:"none",
      rejectIcon:"none",

      accept: () => {
        this.customerService.delete(customer.identification).subscribe({
          complete:(()=>{
            this.getAll();
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Customer deleted' });
          })
        })
      },
    });
  }
}
